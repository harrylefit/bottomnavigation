package harryle.eazy.vn.bottomnavigation.navigation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import harryle.eazy.vn.bottomnavigation.R;
import harryle.eazy.vn.bottomnavigation.helper.NavigationHelper;
import harryle.eazy.vn.bottomnavigation.viewholder.EazyNavigationViewHolder;
import harryle.eazy.vn.bottomnavigation.viewholder.EazyScrollableNavigationViewHolder;
import harryle.eazy.vn.bottomnavigation.viewholder.NavigationViewHolder;

/**
 * Created by harryle on 8/17/17.
 */

public class BottomNavigation extends FrameLayout implements Navigation, ViewPager.OnPageChangeListener {
    private final int DEFAULT_MENU_RES = -1;
    private final String EXTRA_CRR_INDEX_TAB = BottomNavigation.class.getSimpleName() + "-CRR_INDEX_TAB";
    private @MenuRes
    int mMenuRes = DEFAULT_MENU_RES;
    private ViewPager mViewPager;
    private Context mContext;
    private ViewGroup mMainLayout;
    private Menu mMenu;
    private NavigationViewHolder mNavigationViewHolder;
    private NavigationBundle mNavigationBundle;

    public BottomNavigation(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public BottomNavigation(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BottomNavigation(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void setOnNavigationListener(OnNavigationListener onNavigationListener) {
        this.mNavigationBundle.setOnNavigationListener(onNavigationListener);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        Resources mResources = context.getResources();

        if (mNavigationBundle == null) {
            mNavigationBundle = new NavigationBundle();
        }

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BottomNavigation);
            try {
                mNavigationBundle.setActiveColor(ta.getColor(R.styleable.BottomNavigation_activeColor, Color.BLACK));
                mNavigationBundle.setInactiveColor(ta.getColor(R.styleable.BottomNavigation_inActiveColor, Color.GRAY));
                mNavigationBundle.setScrollable(ta.getBoolean(R.styleable.BottomNavigation_isScrollable, false));
                mNavigationBundle.setBackgroundColor(ta.getColor(R.styleable.BottomNavigation_backgroundNavigation, Color.WHITE));
                mNavigationBundle.setTextSize(ta.getDimensionPixelSize(R.styleable.BottomNavigation_textSize, NavigationHelper.dpToPx(context, 14)));
                mNavigationBundle.setSizeOfIcon(ta.getDimensionPixelSize(R.styleable.BottomNavigation_sizeOfIcon, NavigationHelper.dpToPx(context, 10)));
                mNavigationBundle.setDefaultSelectedPosition(ta.getInt(R.styleable.BottomNavigation_defaultSelectedPosition, 0));
                mNavigationBundle.setMode(ta.getInt(R.styleable.BottomNavigation_mode, 1));
                mNavigationBundle.setFontFamily(ta.getResourceId(R.styleable.BottomNavigation_textFontFamily, -1));
                mNavigationBundle.setEnableTintColor(ta.getBoolean(R.styleable.BottomNavigation_enableTintColor, true));
                mNavigationBundle.setHasNotification(ta.getBoolean(R.styleable.BottomNavigation_hasNotification, false));
                mNavigationBundle.setHideTitle(ta.getBoolean(R.styleable.BottomNavigation_hideTitle, false));
                int resMenu = ta.getResourceId(R.styleable.BottomNavigation_resMenu, -1);
                if (resMenu != -1) {
                    setMenu(resMenu);
                }
            } finally {
                ta.recycle();
            }
        }
        setClipToPadding(false);
    }

    private void createMainLayout(boolean isScrollable) {
        if (!isScrollable) {
            mMainLayout = generateMainLayout();
            mNavigationViewHolder = new EazyNavigationViewHolder(mMenu, (LinearLayout) mMainLayout, mNavigationBundle);
        } else {
            mMainLayout = generateMainLayoutWithScrollable();
            mNavigationViewHolder = new EazyScrollableNavigationViewHolder(mMenu, (HorizontalScrollView) mMainLayout, mNavigationBundle);
        }
        mMainLayout.setBackgroundColor(mNavigationBundle.getBackgroundColor());
        addView(mMainLayout);
        mNavigationViewHolder.updateLayout();
    }

    private HorizontalScrollView generateMainLayoutWithScrollable() {
        HorizontalScrollView ly = new HorizontalScrollView(this.mContext);
        ly.setFillViewport(true);
        ly.setBackgroundColor(mNavigationBundle.getBackgroundColor());
        FrameLayout.LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, NavigationHelper.dpToPx(this.mContext, 56));
        ly.setLayoutParams(lp);
        return ly;
    }

    private LinearLayout generateMainLayout() {
        LinearLayout ly = new LinearLayout(this.mContext);
        ly.setWeightSum(mMenu.size());
        ly.setBackgroundColor(mNavigationBundle.getBackgroundColor());
        ly.setOrientation(LinearLayout.HORIZONTAL);
        ly.setGravity(Gravity.CENTER);
        FrameLayout.LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, NavigationHelper.dpToPx(this.mContext, 56));
        lp.gravity = Gravity.CENTER;
        ly.setLayoutParams(lp);
        return ly;
    }


    private Menu parseMenuRes(@MenuRes int resMenu) {
        PopupMenu p = new PopupMenu(this.mContext, null);
        p.inflate(resMenu);
        return p.getMenu();
    }

    @Override
    public void setMenu(int menuRes) {
        this.mMenuRes = menuRes;
        mMenu = parseMenuRes(this.mMenuRes);
        createMainLayout(mNavigationBundle.isScrollable());
    }

    /**
     * Example when creating Dynamic menu
     *
     */
    @Override
    public void setMenu(Menu menu) {
        //this.mMenuRes = menuRes;
        mMenu = menu;
        createMainLayout(mNavigationBundle.isScrollable());
    }

    @Override
    public void attachToRecyclerView(RecyclerView rv) {
    }

    @Override
    public void detachFromRecyclerView() {
    }

    @Override
    public void show() {
        setVisibility(VISIBLE);
    }

    @Override
    public void hide() {
        setVisibility(GONE);
    }

    @Deprecated
    @Override
    public void showWithAnimation() {
        Log.d("BottomNavigation", "Coming soon");
    }

    @Deprecated
    @Override
    public void hideWithAnimation() {
        Log.d("BottomNavigation", "Coming soon");
    }

    public void updateNotification(int index, int number) {
        mNavigationViewHolder.updateNotification(index, number);
    }

    public void disableTabs(int... indexes) {
        mNavigationViewHolder.disableTabs(indexes);
    }

    public void activeTabs(int... indexes) {
        mNavigationViewHolder.activeTabs(indexes);
    }

    public void activeAllTabs() {
        mNavigationViewHolder.activeAllTabs();
    }

    public void disableAllTabs() {
        mNavigationViewHolder.disableAllTabs();
    }

    @Override
    public void setupWithViewPager(ViewPager viewPager) {
        if (mNavigationBundle.getMode() == 0) {
            this.mViewPager = viewPager;
            this.mViewPager.addOnPageChangeListener(this);
            mNavigationViewHolder.updateStateOfTabsMenu(0);
            mNavigationBundle.setViewPager(this.mViewPager);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("BottomNavigation", "onPageScrolled");
    }

    @Override
    public void onPageSelected(int position) {
        if (mNavigationBundle.getMode() == 0) {
            mNavigationViewHolder.updateStateOfTabsMenu(position);
        }
    }

    public void updateStateOfTabsMenu(int position) {
        mNavigationViewHolder.updateStateOfTabsMenu(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d("BottomNavigation", "onPageScrollStateChanged");
    }

    public int getCurrentPosition() throws IllegalAccessException {
        if(mNavigationViewHolder == null){
            throw new IllegalAccessException("You must set menu first");
        }
        return mNavigationViewHolder.getCurrentPosition();
    }

    public void onSaveInstanceStateView(Bundle outState) {
        if (outState != null) {
            try {
                outState.putInt(EXTRA_CRR_INDEX_TAB, getCurrentPosition());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void onRestoreInstanceStateView(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            int index = savedInstanceState.getInt(EXTRA_CRR_INDEX_TAB, 0);
            mNavigationViewHolder.updateStateOfTabsMenu(index);
        }
    }

}
