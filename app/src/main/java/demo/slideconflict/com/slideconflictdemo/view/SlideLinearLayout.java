package demo.slideconflict.com.slideconflictdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import demo.slideconflict.com.slideconflictdemo.LogUtils;

/**
 * 跟随手指滑动回弹
 */
public class SlideLinearLayout extends LinearLayout {


    private int mStartY;
    private int mStartX;
    private int mMoveYDis;
    private int mMoveXDis;

    public SlideLinearLayout(Context context) {
        super(context);
    }

    public SlideLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int EVENT = event.getAction();
        switch (EVENT) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("ACTION_DOWN");
                //获取点击的位置
                mStartY = (int) event.getY();
                mStartX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("ACTION_MOVE");

                //计算X，Y 移动的距离并移动控件
                int yDiff = (int) event.getY() - mStartY;
//                int xDiff = (int) event.getX() - mStartX;
                mMoveYDis += yDiff;
                layout(getLeft(), getTop() + yDiff, getRight(), getBottom() + yDiff);
//                mMoveXDis += xDiff;
//                layout(getLeft() + xDiff, getTop(), getRight() + xDiff, getBottom());

                break;
            case MotionEvent.ACTION_UP:
                //恢复到初始位置
                layout(getLeft() - mMoveXDis, getTop() - mMoveYDis, getRight() - mMoveXDis, getBottom() - mMoveYDis);
                mMoveYDis = 0;
                mMoveXDis = 0;
                break;
        }
        return true;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int EVENT = ev.getAction();
        switch (EVENT) {
            case MotionEvent.ACTION_DOWN:
                mStartY=(int)ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if((int)ev.getY()>mStartY){

                  return  true;
                }else{
                    return  false;
                }
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);

    }
}
