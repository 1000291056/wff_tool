package com.wff.androidtool.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;


import com.wff.androidtool.ICallBack;
import com.wff.androidtool.IMyAidlInterface;
import com.wff.androidtool.bean.MessageBean;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wufeifei on 2017/1/12.
 */

public class SocketService extends Service {
    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static Socket clentSocket;
    private static final int PORT = 8989;
    private static final String HOST = "10.108.6.89";
    private MessageBean mMessageBean;
    private ReadTask rt;
    private WriteTask wt;
    private ICallBack mICallBack;
    private IMyAidlInterface.Stub mStub = new IMyAidlInterface.Stub() {

        @Override
        public void setMessage(MessageBean m) throws RemoteException {
            mMessageBean = m;
            if (wt != null) {
                wt.setMessag(m);
            }

        }

        @Override
        public void setICallBacl(ICallBack c) throws RemoteException {
            mICallBack = c;
            if (rt!=null){
                rt.setICallBack(mICallBack);
            }

        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        startWork();
        return mStub;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * @param intent
     * @param flags
     * @param startId
     * @return 这个整形可以有四个返回值：start_sticky、start_no_sticky、START_REDELIVER_INTENT、START_STICKY_COMPATIBILITY。
     * 它们的含义分别是：
     * 1):START_STICKY：如果service进程被kill掉，保留service的状态为开始状态，但不保留递送的intent对象。随后系统会尝试重新创建service，由于服务状态为开始状态，所以创建服务后一定会调用onStartCommand(Intent,int,int)方法。如果在此期间没有任何启动命令被传递到service，那么参数Intent将为null。
     * 2):START_NOT_STICKY：“非粘性的”。使用这个返回值时，如果在执行完onStartCommand后，服务被异常kill掉，系统不会自动重启该服务
     * 3):START_REDELIVER_INTENT：重传Intent。使用这个返回值时，如果在执行完onStartCommand后，服务被异常kill掉，系统会自动重启该服务，并将Intent的值传入。
     * 4):START_STICKY_COMPATIBILITY：START_STICKY的兼容版本，但不保证服务被kill后一定能重启。
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startWork();
        return START_REDELIVER_INTENT;
    }

    private void startWork() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    clentSocket = new Socket(HOST, PORT);
                    rt = new ReadTask(clentSocket);
                    rt.setICallBack(mICallBack);
                    wt = new WriteTask(clentSocket);
                    executorService.execute(rt);
                    executorService.execute(wt);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
