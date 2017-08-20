package harryle.eazy.vn.bottomnavigation.viewholder;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import harryle.eazy.vn.bottomnavigation.navigation.NavigationBundle;

/**
 * Created by harryle on 8/17/17.
 */

public class EazyScrollableNavigationViewHolder extends NavigationViewHolder<HorizontalScrollView> {
    public EazyScrollableNavigationViewHolder(Menu menu, HorizontalScrollView view, NavigationBundle navigationBundle) {
        super(menu, view, navigationBundle);
    }

    @Override
    protected ViewGroup createItem(MenuItem menuItem) {
        LinearLayout lyItem = new LinearLayout(view.getContext());
        lyItem.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.weight = 1;
        lyItem.setLayoutParams(lp);

        //Todo create ImageView
        ivIcon = new AppCompatImageView(view.getContext());
        LinearLayout.LayoutParams lpIcon = new LinearLayout.LayoutParams(sizeOfIcon, sizeOfIcon);
        ivIcon.setLayoutParams(lpIcon);
        ivIcon.setAdjustViewBounds(true);
        if (menuItem.getIcon() != null) {
            ivIcon.setImageDrawable(menuItem.getIcon());
        }
        lyItem.addView(ivIcon);

        //Todo create Title Textview
        tvTitle = new AppCompatTextView(view.getContext());
        if (!TextUtils.isEmpty(menuItem.getTitle())) {
            tvTitle.setText(menuItem.getTitle());
        }
        lyItem.addView(tvTitle);
        view.addView(lyItem);
        return lyItem;
    }
}
