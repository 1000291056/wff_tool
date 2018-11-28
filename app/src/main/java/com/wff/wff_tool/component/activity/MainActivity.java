package com.wff.wff_tool.component.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.StrictMode;
import android.support.annotation.NonNull;
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

import com.wff.wff_tool.ICallBack;
import com.wff.wff_tool.IMyAidlInterface;
import com.wff.wff_tool.R;
import com.wff.wff_tool.asyctask.MtAsycTask;
import com.wff.wff_tool.bean.MessageBean;
import com.wff.wff_tool.dragger.DraggerActivity;
import com.wff.wff_tool.eventbus.EventBusActivity;
import com.wff.wff_tool.nativecode.NativeTest;
import com.wff.wff_tool.okio.OkIO;
import com.wff.wff_tool.component.receiver.OrientationBroadcastReceiver;
import com.wff.wff_tool.rxjava.RxJava;
import com.wff.wff_tool.ui.view.CustomLineLayout;
import com.wff.wff_tool.utils.OpenGLActivity;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.imageload)
    Button imageload;
    private OrientationBroadcastReceiver orientationBR = new OrientationBroadcastReceiver();
    private IntentFilter orientationIF = new IntentFilter(Intent.ACTION_CONFIGURATION_CHANGED);

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
    private static List<MessageBean> list = new ArrayList<>();
    private Handler mHan = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            list.add(new MessageBean());
            sendEmptyMessage(1);
        }
    };

    private void testOOM() {
        mHan.sendEmptyMessage(1);
    }

    private void testOOM(int i) {
        mHan.sendEmptyMessage(1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        position(3);
        isTaskRoot();
//        testOOM();
        ButterKnife.bind(this);
        //NetWork.searchBook();
        new OkIO().testOkio();
        PackageManager packageManager = getPackageManager();
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(mContext.getPackageName(), "person", 1);
        int ma = matcher.match(Uri.parse("content://" + mContext.getPackageName() + "/person"));
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectActivityLeaks().build());
        new RxJava(this).rxjava();
        Log.i(TAG, "onCreate____________________");
//        new NativeTest();
//        pixelProcessing();
        // Log.i(TAG, "bind service" + bindService(new Intent(MainActivity.this, SocketService.class), mServiceConnection, Context.BIND_AUTO_CREATE));
//        for (int i = 0; i < 10; i++) {
//            mHandler.sendEmptyMessageDelayed(0, (i + 1) * 1000);
//        }
//        MqttC
    }

    private static final int BITS_PER_UNIT = 8;

    private int position(int idx) { // bits big-endian in each unit
        return 1 << (BITS_PER_UNIT - 1 - (idx % BITS_PER_UNIT));
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
    protected void onResume() {
        super.onResume();
        //this.registerReceiver(orientationBR, orientationIF);
    }

    @Override
    protected void onPause() {
        //Unregister the Orientation BroadcasReceiver to avoid a BroadcastReceiver leak
//        this.unregisterReceiver(orientationBR);
        super.onPause();
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
        //asycTask.execute();
//        asycTask.executeOnExecutor(new Executor() {
//            @Override
//            public void execute(@NonNull Runnable command) {
//                MtAsycTask.THREAD_POOL_EXECUTOR.execute();
//            }
//        });
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
            , R.id.testProAnimator, R.id.testPullView
            , R.id.openglBtnView, R.id.testTransition
            , R.id.testrefresh_recycle, R.id.test_touchevent_btn
            , R.id.imageload, R.id.viewpager, R.id.dragger2
            , R.id.eventbus,R.id.custom_ll,R.id.vector})
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
            case R.id.test_touchevent_btn:
                startActivity(new Intent(mContext, TestTouchEventActivity.class));
                break;
            case R.id.imageload:
                startActivity(new Intent(mContext, ImageLoaderActivity.class));
                break;
            case R.id.viewpager:
                startActivity(new Intent(mContext, ViewpagerActivity.class));
                break;
            case R.id.dragger2:
                startActivity(new Intent(mContext, DraggerActivity.class));
                break;
            case R.id.eventbus:
                startActivity(new Intent(mContext, EventBusActivity.class));
                break;
            case R.id.custom_ll:
                startActivity(new Intent(mContext, CustomLinelayoutActivity.class));
                break;
            case R.id.vector:
                startActivity(new Intent(mContext, VectorActivity.class));
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
