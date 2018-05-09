package com.wff.wff_tool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wff.wff_tool.asyctask.MtAsycTask;
import com.wff.wff_tool.bean.MessageBean;
import com.wff.wff_tool.nativecode.NativeObject;
import com.wff.wff_tool.nativecode.NativeTest;
import com.wff.wff_tool.socket.SocketService;
import com.wff.wff_tool.utils.OpenGLActivity;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;

/**
 * @author wufeifei
 */
public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.drawpathBtn)
    Button mDrawpathBtn;
    @BindView(R.id.openglBtn)
    Button mOpenglBtn;
    @BindView(R.id.pixel_processing_image)
    ImageView mPixelProcessingImage;
    @BindView(R.id.msg)
    EditText mMsgEdt;
    @BindView(R.id.sendMsg)
    Button mSendMsgBtn;
    @BindView(R.id.receiveMsgTv)
    TextView mReceiveMsgTv;
    @BindView(R.id.testScrollerBtn)
    Button mTestScrollerBtn;
    @BindView(R.id.activity_main)
    LinearLayout mActivityMain;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Date nowTime = new Date();
            SimpleDateFormat time = new SimpleDateFormat("yyyy MM dd HH mm ss");
            Log.i(MainActivity.class.getSimpleName(), time.format(nowTime));
        }
    };
    private IMyAidlInterface mInterface;
    private ICallBack.Stub mStub = new ICallBack.Stub() {
        @Override
        public void setMsg(final String msg) throws RemoteException {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mReceiveMsgTv.setText(msg);
                }
            });

        }
    };
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mInterface = IMyAidlInterface.Stub.asInterface(service);
            try {
                mInterface.setICallBacl(mStub);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Toast.makeText(MainActivity.this, "成功连接", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "ComponentName" + name.getClassName());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "ComponentName" + name.getClassName());
            Toast.makeText(MainActivity.this, "失去连接", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.i(TAG, "onCreate____________________");
//        new NativeTest();
//        pixelProcessing();
        Log.i(TAG, "bind service" + bindService(new Intent(MainActivity.this, SocketService.class), mServiceConnection, Context.BIND_AUTO_CREATE));
//        for (int i = 0; i < 10; i++) {
//            mHandler.sendEmptyMessageDelayed(0, (i + 1) * 1000);
//        }
//        MqttC
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart  _____________" +
                "" +
                "" +
                "");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart _______________");
    }

    @Override
    public void onStateNotSaved() {
        super.onStateNotSaved();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop____________________");
    }

    private void testApi() {
        LruCache<String, Bitmap> lruCache = new LruCache<>(10);
    }

    private void testAsync() {
        MtAsycTask asycTask = new MtAsycTask();
        asycTask.execute();
    }

    private void testClass() {
//    ClassLoader
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /**
     * 图片像素处理     变灰
     */
    private void pixelProcessing() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[width * height];
        int alpha, grey, red, blue, green;
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        alpha = 0xFF << 24;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grey = pixels[width * i + j];
                red = ((grey & 0x00FF0000) >> 16);
                green = ((grey & 0x0000FF00) >> 8);
                blue = ((grey & 0x000000FF));
                grey = (int) ((float) red * 0.3 + (float) green * 0.59 + (float) blue * 0.11);
                grey = alpha | (grey << 16) | (grey << 8) | grey;

                pixels[width * i + j] = grey;
            }
        }
        Bitmap pro = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        pro.setPixels(pixels, 0, width, 0, 0, width, height);
        bitmap.recycle();
        mPixelProcessingImage.setImageBitmap(pro);
    }

    private void testRxjava() {
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        });
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {


            }
        };
        observable.lift(new Observable.Operator<Integer, String>() {

            @Override
            public Subscriber<? super String> call(Subscriber<? super Integer> subscriber) {
                return new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                    }
                };
            }
        }).subscribe(subscriber);
    }

    private void testHttp() {
        try {
            URL url = new URL("");
            url.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.drawpathBtn, R.id.openglBtn
            , R.id.sendMsg, R.id.testScrollerBtn
            ,R.id.testProAnimator,R.id.testPullView
            ,R.id.openglBtnView,R.id.testTransition
    ,R.id.testrefresh_recycle})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.drawpathBtn:
                startActivity(new Intent(mContext, DrawPathActivity.class));
                break;
            case R.id.openglBtn:
                startActivity(new Intent(mContext, MyOpenGLActivity.class));
                break;
            case R.id.testScrollerBtn:
                startActivity(new Intent(mContext, TestScrollerActivity.class));
                break;
            case R.id.testProAnimator:
                startActivity(new Intent(mContext, TestAnimatorActivity.class));
                break;
            case R.id.testPullView:
                startActivity(new Intent(mContext, PullRefreshActivity.class));
                break;
            case R.id.openglBtnView:
                startActivity(new Intent(mContext, OpenGLActivity.class));
                break;
            case R.id.testTransition:
                startActivity(new Intent(mContext, TestTransitionActivity.class));
                break;
            case R.id.testrefresh_recycle:
                startActivity(new Intent(mContext, TestRefreshActivity.class));
                break;
            case R.id.sendMsg:
                if (mInterface != null) {
                    try {
                        mInterface.setMessage(new MessageBean(mMsgEdt.getText().toString()));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    } finally {
                        mMsgEdt.setText("");
                    }
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unbindService(mServiceConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
