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

    public NavigationViewHolder(Menu menu, V view, NavigationBundle navigationBundle) {
        this.mMenu = menu;
        this.view = view;
        this.navigationBundle = navigationBundle;
        sizeOfIcon = NavigationHelper.dpToPx(view.getContext(), 20);
    }

    protected abstract ViewGroup createItem(MenuItem menuItem);

    public void updateLayout() {
        if (mMenu != null) {
            for (int i = 0; i < mMenu.size(); i++) {
                if (tabsMenu == null) {
                    tabsMenu = new ArrayList<>();
                }
                tabsMenu.add(createItem(mMenu.getItem(i)));
            }
        }
    }

    public void updateStateOfTabsMenu(int selectedPosition) {
        if (tabsMenu != null) {
            for (ViewGroup item : tabsMenu) {
                if (selectedPosition == tabsMenu.indexOf(item)) {
                    NavigationHelper.refreshStateOfTabMenu(item, navigationBundle.getActiveColor());
                } else {
                    NavigationHelper.refreshStateOfTabMenu(item, navigationBundle.getInactiveColor());
                }
            }
        }
    }
}
