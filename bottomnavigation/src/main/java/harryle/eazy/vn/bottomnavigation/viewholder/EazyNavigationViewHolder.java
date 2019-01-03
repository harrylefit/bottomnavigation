package harryle.eazy.vn.bottomnavigation.viewholder;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import harryle.eazy.vn.bottomnavigation.R;
import harryle.eazy.vn.bottomnavigation.helper.NavigationHelper;
import harryle.eazy.vn.bottomnavigation.navigation.NavigationBundle;

/**
 * Created by harryle on 8/17/17.
 */

public class EazyNavigationViewHolder extends NavigationViewHolder<LinearLayout> implements View.OnClickListener {
    private Typeface typeface;

    public EazyNavigationViewHolder(Menu menu, LinearLayout view, NavigationBundle navigationBundle) {
        super(menu, view, navigationBundle);
        if(view.isInEditMode()) return;
        if (navigationBundle.getFontFamily() != -1) {
            typeface = ResourcesCompat.getFont(view.getContext(), navigationBundle.getFontFamily());
        }
    }

    @Override
    protected ViewGroup createItem(MenuItem menuItem, int index) {
        FrameLayout frameLayout = new FrameLayout(view.getContext());
        frameLayout.setClickable(true);
        LinearLayout.LayoutParams mainLp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        mainLp.weight = 1;
        frameLayout.setLayoutParams(mainLp);
        LinearLayout lyItem = new LinearLayout(view.getContext());
        lyItem.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lyItem.setLayoutParams(lp);

        //Todo create ImageView
        ivIcon = new AppCompatImageView(view.getContext());
        LinearLayout.LayoutParams lpIcon = new LinearLayout.LayoutParams(sizeOfIcon, sizeOfIcon);
        lpIcon.gravity = Gravity.CENTER;
        lpIcon.bottomMargin = NavigationHelper.dpToPx(view.getContext(), 3);
        ivIcon.setLayoutParams(lpIcon);
        ivIcon.setAdjustViewBounds(true);
        if (menuItem.getIcon() != null) {
            ivIcon.setImageDrawable(menuItem.getIcon());
        }
        ivIcon.setImageTintMode(PorterDuff.Mode.SRC_IN);
        frameLayout.setTag(R.id.tagIcon, ivIcon);

        lyItem.addView(ivIcon);

        if(!navigationBundle.isHideTitle()) {
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
            if (typeface != null) {
                tvTitle.setTypeface(typeface);
            }
            frameLayout.setTag(R.id.tagTitle, tvTitle);
            lyItem.addView(tvTitle);
        }

        frameLayout.addView(lyItem);

        FrameLayout notificationView = (FrameLayout) LayoutInflater.from(view.getContext()).inflate(R.layout.view_tab, null);
        FrameLayout.LayoutParams notificationLp = new FrameLayout.LayoutParams(NavigationHelper.dpToPx(view.getContext(), 15), NavigationHelper.dpToPx(view.getContext(), 15));
        notificationLp.rightMargin = NavigationHelper.dpToPx(view.getContext(), 15);
        notificationLp.setMarginEnd(NavigationHelper.dpToPx(view.getContext(), 15));
        notificationLp.gravity = Gravity.RIGHT | Gravity.TOP;
        notificationView.setLayoutParams(notificationLp);
        frameLayout.addView(notificationView);
        notificationView.setVisibility(View.INVISIBLE);
        view.addView(frameLayout);

        NavigationHelper.refreshStateOfTabMenu(lyItem, navigationBundle.getInactiveColor(), false, navigationBundle.isEnableTintColor());
        frameLayout.setTag(index);
        frameLayout.setOnClickListener(this);

        return frameLayout;
    }

    @Override
    public void onClick(View view) {
        if (view.getTag(R.id.tagPreventTouch) != null && (boolean) view.getTag(R.id.tagPreventTouch)) {
            return;
        }
        if (navigationBundle.getMode() == 0) {
            navigationBundle.getViewPager().setCurrentItem(tabsMenu.indexOf(view), true);
        } else {
            int index = (int) view.getTag();
            updateStateOfTabsMenu(index);
        }
        if (navigationBundle.getOnNavigationListener() != null) {
            navigationBundle.getOnNavigationListener().onClickNavigationItem(tabsMenu.indexOf(view));
        }
    }
}
