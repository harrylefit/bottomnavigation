package harryle.eazy.vn.bottomnavigation.viewholder;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import harryle.eazy.vn.bottomnavigation.R;
import harryle.eazy.vn.bottomnavigation.helper.NavigationHelper;
import harryle.eazy.vn.bottomnavigation.navigation.NavigationBundle;

/**
 * Created by harryle on 8/17/17.
 */

public abstract class NavigationViewHolder<V extends ViewGroup> {
    protected Menu mMenu;
    protected V view;
    protected final int sizeOfIcon;
    protected AppCompatImageView ivIcon;
    protected AppCompatTextView tvTitle;
    protected NavigationBundle navigationBundle;
    protected List<ViewGroup> tabsMenu;
    protected int currentPosition;

    public NavigationViewHolder(Menu menu, V view, NavigationBundle navigationBundle) {
        this.mMenu = menu;
        this.view = view;
        this.navigationBundle = navigationBundle;
        sizeOfIcon = NavigationHelper.dpToPx(view.getContext(), navigationBundle.getSizeOfIcon());
        currentPosition = navigationBundle.getDefaultSelectedPosition();
    }

    protected abstract ViewGroup createItem(MenuItem menuItem, int index);

    public void updateLayout() {
        if (mMenu != null) {
            for (int i = 0; i < mMenu.size(); i++) {
                if (tabsMenu == null) {
                    tabsMenu = new ArrayList<>();
                }
                tabsMenu.add(createItem(mMenu.getItem(i), i));
            }
        }
        if (navigationBundle.isEnableRippleEffect()) {
            for (ViewGroup menu : tabsMenu) {
                menu.setBackgroundResource(R.drawable.ripple);
            }
        }
        if (navigationBundle.getMode() == 1) {
            updateStateOfTabsMenu(navigationBundle.getDefaultSelectedPosition());
        }
    }

    public void updateStateOfTabsMenu(int selectedPosition) {
        if (tabsMenu != null) {
            for (ViewGroup item : tabsMenu) {
                if (selectedPosition == tabsMenu.indexOf(item)) {
                    currentPosition = selectedPosition;
                    NavigationHelper.refreshStateOfTabMenu(item, navigationBundle.getActiveColor()
                            , true, navigationBundle.isEnableTintColor());
                } else {
                    NavigationHelper.refreshStateOfTabMenu(item, navigationBundle.getInactiveColor()
                            , false, navigationBundle.isEnableTintColor());
                }
            }
        }
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void updateNotification(int index, int number) {
        if (tabsMenu != null) {
            ViewGroup view = tabsMenu.get(index);
            if (view != null) {
                NavigationHelper.updateNotification(view, number);
            }
        }
    }

    public void disableTabs(int... indexes) {
        if (tabsMenu != null) {
            for (int index : indexes) {
                ViewGroup view = tabsMenu.get(index);
                if (view != null) {
                    view.setTag(R.id.tagPreventTouch, true);
                }
            }
        }
    }

    public void activeTabs(int... indexes) {
        if (tabsMenu != null) {
            for (int index : indexes) {
                ViewGroup view = tabsMenu.get(index);
                if (view != null) {
                    view.setTag(R.id.tagPreventTouch, false);
                }
            }
        }
    }

    public void disableAllTabs() {
        int[] indexes = getAllTabIndexes();
        disableTabs(indexes);
    }

    public void activeAllTabs() {
        int[] indexes = getAllTabIndexes();
        activeTabs(indexes);
    }

    private int[] getAllTabIndexes() {
        int[] indexes = new int[tabsMenu.size()];
        for (int i = 0; i < tabsMenu.size(); i++) {
            indexes[i] = i;
        }
        return indexes;
    }
}
