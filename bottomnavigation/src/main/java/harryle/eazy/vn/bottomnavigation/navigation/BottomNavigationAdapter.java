package harryle.eazy.vn.bottomnavigation.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harryle on 8/20/17.
 */

public class BottomNavigationAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public BottomNavigationAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
    }

    public void addFragment(Fragment fragment) {
        list.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        if (list == null)
            return 0;
        return list.size();
    }
}
