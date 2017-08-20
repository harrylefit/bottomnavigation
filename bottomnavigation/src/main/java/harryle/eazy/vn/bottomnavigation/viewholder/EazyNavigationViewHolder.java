package harryle.eazy.vn.bottomnavigation.viewholder;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageHelper;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import harryle.eazy.vn.bottomnavigation.R;
import harryle.eazy.vn.bottomnavigation.helper.NavigationHelper;
import harryle.eazy.vn.bottomnavigation.navigation.NavigationBundle;

/**
 * Created by harryle on 8/17/17.
 */

public class EazyNavigationViewHolder extends NavigationViewHolder<LinearLayout> implements View.OnClickListener {
    public EazyNavigationViewHolder(Menu menu, LinearLayout view, NavigationBundle navigationBundle) {
        super(menu, view, navigationBundle);
    }

    @Override
    protected ViewGroup createItem(MenuItem menuItem) {
        LinearLayout lyItem = new LinearLayout(view.getContext());
        lyItem.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.weight = 1;
        lyItem.setLayoutParams(lp);

        //Todo create ImageView
        ivIcon = new AppCompatImageView(view.getContext());
        LinearLayout.LayoutParams lpIcon = new LinearLayout.LayoutParams(sizeOfIcon, sizeOfIcon);
        lpIcon.gravity = Gravity.CENTER;
        ivIcon.setLayoutParams(lpIcon);
        ivIcon.setAdjustViewBounds(true);
        if (menuItem.getIcon() != null) {
            ivIcon.setImageDrawable(menuItem.getIcon());
        }
        ivIcon.setSupportImageTintMode(PorterDuff.Mode.SRC_IN);
        lyItem.setTag(R.id.tagIcon, ivIcon);

        lyItem.addView(ivIcon);

        //Todo create Title Textview
        tvTitle = new AppCompatTextView(view.getContext());
        LinearLayout.LayoutParams lpTitle = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpTitle.gravity = Gravity.CENTER;
        tvTitle.setLayoutParams(lpTitle);
        tvTitle.setMaxLines(1);
        tvTitle.setEllipsize(TextUtils.TruncateAt.END);
        if (!TextUtils.isEmpty(menuItem.getTitle())) {
            tvTitle.setText(menuItem.getTitle());
        }

        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, navigationBundle.getTextSize());
        lyItem.setTag(R.id.tagTitle, tvTitle);
        lyItem.addView(tvTitle);
        view.addView(lyItem);

        NavigationHelper.refreshStateOfTabMenu(lyItem, navigationBundle.getInactiveColor());
        lyItem.setOnClickListener(this);
        return lyItem;
    }

    @Override
    public void onClick(View view) {
        navigationBundle.getViewPager().setCurrentItem(tabsMenu.indexOf(view),true);
        if (navigationBundle.getOnNavigationListener() != null) {
            navigationBundle.getOnNavigationListener().onClickNavigationItem(tabsMenu.indexOf(view));
        }
    }
}
