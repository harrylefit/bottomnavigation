package harryle.eazy.vn.bottomnavigation.helper;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import harryle.eazy.vn.bottomnavigation.R;

/**
 * Created by harryle on 8/17/17.
 */

public class NavigationHelper {
    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }


    public static void refreshStateOfTabMenu(ViewGroup view, int color) {
        if (view != null && view.getChildCount() > 0) {
            try {
                AppCompatImageView ivIcon = (AppCompatImageView) view.getTag(R.id.tagIcon);
                if (ivIcon != null) {
                    ivIcon.setSupportImageTintList(ColorStateList.valueOf(color));
                }

                AppCompatTextView tvTitle = (AppCompatTextView) view.getTag(R.id.tagTitle);
                if (tvTitle != null) {
                    tvTitle.setTextColor(color);
                }
            } catch (ClassCastException ex) {
                ex.printStackTrace();
            }
        }
    }

}
