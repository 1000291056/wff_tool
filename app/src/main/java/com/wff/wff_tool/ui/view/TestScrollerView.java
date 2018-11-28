package com.wff.wff_tool.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by wufeifei on 2017/2/13.
 */

public class TestScrollerView extends android.support.v7.widget.AppCompatTextView implements View.OnTouchListener {
    private Context context;
    private Scroller scroller;

    public TestScrollerView(Context context) {
        super(context);
        initDataa(context);
    }

    public TestScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDataa(context);
    }

    public TestScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDataa(context);
    }

    private void initDataa(Context context) {
        this.context = context;
        this.setOnTouchListener(this);
        scroller = new Scroller(context);
        setText("图中绿色的区域是View的区域，黄色部分是View中的内容部分，我们所操纵的坐标都是相对于当前View而言的。假设原先内容已经移动到（50，50）位置，现在我们点击了（100，100）位置，之后内容移动到我们点击的（100，100）上。 \n" +
                "1:当view的内容在（50，50）时调用 \n" +
                "int scrollX = this.getScrollX(); \n" +
                "int scrollY = this.getScrollY(); \n" +
                "后scrollX =-50;scrollY =-50; \n" +
                "2:当点击（100，100）时调用了smoothScrollTo(-(int)event.getX(),-(int)event.getY()); 其中event.getX()=100;event.getY()=100;注意我们传入时两个参数都取反了，这是因为我们所有的计算都是基于mScrollX和mScrollY，当view内容的左边距位于View位置的左边距的右边是为负的。view内容的上边距位于View位置的上边距的下边是为负的。所以destX = -100;destY=-100; \n" +
                "3:调用int deltaX = destX - scrollX; \n" +
                "int deltaY = destY - scrollY;后 \n" +
                "deltaX = -100+50=-50; \n" +
                "deltaY = -100+50 = -50; \n" +
                "4调用scroller.startScroll(scrollX, scrollY, deltaX, deltaY, 1000); \n" +
                "scrollX = -50为负，view内容的左边缘位于View位置左边缘的右边 \n" +
                "scrollY =-50为父，veiw内容的上边缘位于View位置上边缘的下边。 \n" +
                "即位于（50，50）点上 \n" +
                "deltaX = -50;负数将滑动到右侧。 \n" +
                "deltaY = -50;负数将滑动到下侧。 \n" +
                "滑动时间为1000毫秒 \n" +
                "这个函数view的内容将从（50，50）向右下方（100，100）移动。 \n" +
                "5：调用了InValidate（）方法来重绘这个View，重绘时会调用View的draw方法，在draw方法中会调用computeScroll方法。 \n" +
                "6：因为computeScroll在View中只是空实现，所以我们必须自己实现， \n" +
                "if (scroller.computeScrollOffset()) { \n" +
                "this.scrollTo(scroller.getCurrX(), scroller.getCurrY()); \n" +
                "如果动画还没结束，comcomputeScroll会去向Scoller获取当前的scrollX和scrollY；然后通过scrollTo来实现滑动。 \n" +
                "7：调用postInvalidate()来进行二次重绘。这一次的重绘过程和第一次的重绘过程一样，还是会去调用computeScroll方法，继续获取scrollX和scrollY的值，并通过scrollTo滑动到新位置，往复这个过程，直到滑动过程结束。\n" +
                "\n" +
                "总结一下Scroller的工作原理\n" +
                "\n" +
                "Scroller本身并不能滑动，需要和computeScroll配合使用来实现滑动，这个方法通过postInvalidate()不断的让View重绘，每一次的重绘距离上一次滑动的起始时间有一个时间差，computeScrollOffset方法利用这个时间差来计算出view内容的当前滑动位置，知道了滑动位置就可以通过scrollTo方法来滑动。View的每一次重绘都会使得View小幅度的滑动，多次的滑动组成了弹性滑动。\n" +
                "\n");
    }

    private void smoothScrollTo(int destX, int destY) {
        int scrollX = this.getScrollX();
        int scrollY = this.getScrollY();
        int deltaX = destX - scrollX;
        int deltaY = destY - scrollY;
        scroller.startScroll(scrollX, scrollY, deltaX, deltaY, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            this.scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
//                smoothScrollTo(-(int) event.getX(), -(int) event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                smoothScrollTo(-(int) event.getX(), -(int) event.getY());
                break;
        }
        return true;
    }
}