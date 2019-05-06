package com.wff.androidtool.component.activity;

import android.app.Activity;
import android.os.Bundle;

import com.wff.androidtool.ui.view.TestArcDrawView;

public class TestDrawActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TestArcDrawView(this));
    }
}
