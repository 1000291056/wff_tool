package com.wff.wff_tool;

import android.os.Bundle;

import com.wff.wff_tool.ui.view.DragView;
import com.wff.wff_tool.ui.view.TestScrollerView;

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
