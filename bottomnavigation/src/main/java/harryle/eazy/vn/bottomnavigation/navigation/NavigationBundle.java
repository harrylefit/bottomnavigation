package harryle.eazy.vn.bottomnavigation.navigation;

import android.graphics.Color;
import android.support.v4.view.ViewPager;

/**
 * Created by harryle on 8/20/17.
 */

public class NavigationBundle {
    private int activeColor = Color.BLACK;
    private int inactiveColor = Color.GRAY;
    private boolean isScrollable;
    private int backgroundColor = Color.WHITE;
    private int textSize;
    private OnNavigationListener onNavigationListener;
    private ViewPager viewPager;

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public void setActiveColor(int activeColor) {
        this.activeColor = activeColor;
    }

    public void setInactiveColor(int inactiveColor) {
        this.inactiveColor = inactiveColor;
    }

    public void setScrollable(boolean scrollable) {
        isScrollable = scrollable;
    }

    public int getActiveColor() {
        return activeColor;
    }

    public int getInactiveColor() {
        return inactiveColor;
    }

    public boolean isScrollable() {
        return isScrollable;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setOnNavigationListener(OnNavigationListener onNavigationListener) {
        this.onNavigationListener = onNavigationListener;
    }

    public OnNavigationListener getOnNavigationListener() {
        return onNavigationListener;
    }
}
