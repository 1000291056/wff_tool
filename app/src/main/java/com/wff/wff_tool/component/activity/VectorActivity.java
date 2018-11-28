package com.wff.wff_tool.component.activity;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wff.wff_tool.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VectorActivity extends Activity {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.start_btn)
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector);
        ButterKnife.bind(this);
    }

    private void startAnimator() {
        Drawable drawable = img.getDrawable();
        ((Animatable) drawable).start();
    }



    @OnClick(R.id.start_btn)
    public void onViewClicked() {
        startAnimator();
    }
}
