package com.wff.wff_tool;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wufeifei
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.drawpathBtn)
    Button mDrawpathBtn;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Date nowTime=new Date();
            SimpleDateFormat time=new SimpleDateFormat("yyyy MM dd HH mm ss");
            Log.i(MainActivity.class.getSimpleName(), time.format(nowTime));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        for (int i=0;i<10;i++){
            mHandler.sendEmptyMessageDelayed(0,(i+1)*1000);
        }
    }

    @OnClick({R.id.drawpathBtn})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.drawpathBtn:
                startActivity(new Intent(mContext, DrawPathActivity.class));
                break;
        }
    }
}
