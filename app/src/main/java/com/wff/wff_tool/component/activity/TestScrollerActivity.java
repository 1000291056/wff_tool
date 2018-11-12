package com.wff.wff_tool.component.activity;

import android.os.Bundle;

import com.wff.wff_tool.ui.view.DragView;

/**
 * Created by wufeifei on 2017/2/13.
 */

public class TestScrollerActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DragView(mContext));
    }
}
