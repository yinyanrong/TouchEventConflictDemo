package demo.slideconflict.com.slideconflictdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by My on 2016/12/8.
 */
public class ScrollViewIntercept extends ScrollView {
    public ScrollViewIntercept(Context context) {
        super(context);
    }

    public ScrollViewIntercept(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewIntercept(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int  scrollY=getScrollY();
            if(scrollY==0){
                //允许父类截取事件
                getParent().requestDisallowInterceptTouchEvent(false);
            }else{
                //不允许父类截取事件
                getParent().requestDisallowInterceptTouchEvent(true);

            }
        return super.onTouchEvent(ev);
    }
}
