package harryle.eazy.vn.bottomnavigation.navigation;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by harryle on 8/18/17.
 */

public class BottomNavigationViewPager extends ViewPager {
    private boolean enable;

    public BottomNavigationViewPager(Context context) {
        super(context);
    }

    public BottomNavigationViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(this.enable){
            return super.onTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(this.enable) {
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }

    /**
     *
     * @param enable
     */
    public void setPagingEnabled(boolean enable) {
        this.enable = enable;
    }
}
