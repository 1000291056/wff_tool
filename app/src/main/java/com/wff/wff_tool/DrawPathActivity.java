package com.wff.wff_tool;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.wff.wff_tool.ui.view.DrawPathView;

/**
 * Created by wufeifei on 2016/12/9.
 */

public class DrawPathActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(new DrawPathView(this), params);
    }
}
