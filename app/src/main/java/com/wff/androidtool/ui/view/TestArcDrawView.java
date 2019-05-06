package com.wff.androidtool.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class TestArcDrawView extends View {
    public TestArcDrawView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        canvas.drawArc(new RectF(0,0,getMeasuredWidth(),getMeasuredHeight()),150,0
                ,false,paint);
    }
}
