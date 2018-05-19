package harryle.eazy.vn.bottomnavigation.navigation;

import android.graphics.Color;
import android.support.annotation.IdRes;
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
    private int defaultSelectedPosition = 0;
    private boolean enableRippleEffect = false;
    private int mode = 1;
    @IdRes
    private int fontFamily;

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

    public boolean isEnableRippleEffect() {
        return enableRippleEffect;
    }

    public void setEnableRippleEffect(boolean enableRippleEffect) {
        this.enableRippleEffect = enableRippleEffect;
    }

    public int getDefaultSelectedPosition() {
        return defaultSelectedPosition;
    }

    public void setDefaultSelectedPosition(int defaultSelectedPosition) {
        this.defaultSelectedPosition = defaultSelectedPosition;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(int fontFamily) {
        this.fontFamily = fontFamily;
    }
}
