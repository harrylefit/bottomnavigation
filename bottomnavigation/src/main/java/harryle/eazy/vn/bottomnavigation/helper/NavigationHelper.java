package harryle.eazy.vn.bottomnavigation.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import harryle.eazy.vn.bottomnavigation.R;

/**
 * Created by harryle on 8/17/17.
 */

public class NavigationHelper {
    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }


    public static void refreshStateOfTabMenu(ViewGroup view, int color, boolean isSelected, boolean enableTintColor) {
        if (view != null && view.getChildCount() > 0) {
            try {
                AppCompatImageView ivIcon = (AppCompatImageView) view.getTag(R.id.tagIcon);
                if (ivIcon != null) {
                    if (enableTintColor) {
                        ivIcon.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                        ivIcon.setImageTintList(ColorStateList.valueOf(color));
                        ivIcon.setImageTintMode(PorterDuff.Mode.SRC_IN);
                    }
                    ivIcon.setSelected(isSelected);
                }

                AppCompatTextView tvTitle = (AppCompatTextView) view.getTag(R.id.tagTitle);
                if (tvTitle != null) {
                    tvTitle.setTextColor(color);
                    tvTitle.setSelected(isSelected);
                }
            } catch (ClassCastException ex) {
                ex.printStackTrace();
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public static void updateNotification(ViewGroup view, int number) {
        try {
            if (view != null) {
                FrameLayout frameLayout = view.findViewById(R.id.view_notification);
                AppCompatTextView tvNotification = view.findViewById(R.id.tv_notification);
                if (number == 0) {
                    frameLayout.setVisibility(View.INVISIBLE);
                } else {
                    tvNotification.setText(number + "");
                    frameLayout.setVisibility(View.VISIBLE);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
