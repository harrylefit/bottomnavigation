package harryle.eazy.vn.bottomnavigation.viewholder;

import android.content.res.TypedArray;
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
        sizeOfIcon = NavigationHelper.dpToPx(view.getContext(), 20);
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

    private int getRippleBackground() {
        int[] attrs = new int[]{R.attr.selectableItemBackgroundBorderless};
        TypedArray typedArray = view.getContext().obtainStyledAttributes(attrs);
        int backgroundResource = typedArray.getResourceId(0, 0);
        typedArray.recycle();
        return backgroundResource;
    }

    public void updateStateOfTabsMenu(int selectedPosition) {
        if (tabsMenu != null) {
            for (ViewGroup item : tabsMenu) {
                if (selectedPosition == tabsMenu.indexOf(item)) {
                    currentPosition = selectedPosition;
                    NavigationHelper.refreshStateOfTabMenu(item, navigationBundle.getActiveColor(), true);
                } else {
                    NavigationHelper.refreshStateOfTabMenu(item, navigationBundle.getInactiveColor(), false);
                }
            }
        }
    }

    public int getCurrentPosition() {
        return currentPosition;
    }
}
