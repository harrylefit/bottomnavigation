package vn.eazy.bottomnavigation.example

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by harryle on 8/18/17.
 */
class MainPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private val list: MutableList<Fragment> = mutableListOf()

    fun addFragment(fragment: Fragment){
        list.add(fragment)
    }

    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        if (list == null)
            return 0
        return list.size
    }

}