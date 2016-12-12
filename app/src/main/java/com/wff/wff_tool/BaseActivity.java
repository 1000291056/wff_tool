package com.wff.wff_tool;

import android.app.Activity;
import android.content.Context;

/**
 * Created by wufeifei on 2016/12/8.
 */

public class BaseActivity extends Activity {
    protected Context mContext=this;
    public boolean isNormol = false;

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
