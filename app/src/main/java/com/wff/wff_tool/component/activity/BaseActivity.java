package com.wff.wff_tool.component.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by wufeifei on 2016/12/8.
 */

public class BaseActivity extends Activity {
    protected Context mContext=this;
    public boolean isNormol = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isNormol = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isNormol = false;
    }

    public boolean isNormol() {
        return isNormol;
    }

    public void setNormol(boolean normol) {
        isNormol = normol;
    }
}
