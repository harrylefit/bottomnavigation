package harryle.eazy.vn.bottomnavigation.navigation;

import android.support.annotation.MenuRes;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by harryle on 8/17/17.
 */

public interface Navigation {
    /**
     *
     * @param resMenu
     */
    void setMenu(@MenuRes int resMenu);

    /**
     *
     * @param rv
     */
    void attachToRecyclerView(RecyclerView rv);

    /**
     *
     */
    void detachFromRecyclerView();

    /**
     *
     */
    void show();

    /**
     *
     */
    void hide();

    /**
     *
     */
    void showWithAnimation();

    /**
     *
     */
    void hideWithAnimation();

    /**
     *
     * @param viewPager
     */
    void setupWithViewPager(ViewPager viewPager);
}
