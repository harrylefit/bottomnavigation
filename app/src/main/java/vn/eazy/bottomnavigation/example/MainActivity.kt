package vn.eazy.bottomnavigation.example

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.widget.PopupMenu
import harryle.eazy.vn.bottomnavigation.navigation.BottomNavigationAdapter
import harryle.eazy.vn.bottomnavigation.navigation.OnNavigationListener
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Author Harry Le
 */
class MainActivity : AppCompatActivity(), OnNavigationListener {
    companion object {
        val TAG: String = MainActivity.javaClass.simpleName
    }

    private val adapter: BottomNavigationAdapter by lazy { BottomNavigationAdapter(supportFragmentManager) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menuInit() /* Example with Dynamic Menu */
        //menu()
    }

    private fun menu() {
        //        nav.setMenu(R.menu.nav)

        adapter.addFragment(TestFragment.newInstance())
        adapter.addFragment(TestFragment.newInstance())
        adapter.addFragment(TestFragment.newInstance())
        adapter.addFragment(TestFragment.newInstance())

        vpMain.setPagingEnabled(true)
        vpMain.adapter = adapter
        nav.setupWithViewPager(vpMain)
        nav.setOnNavigationListener(this)

        nav.show()
        Log.d(TAG, "Current position : " + nav.currentPosition)

//        //Todo don't do like this (it's just a example)
        Handler().postDelayed({
            nav.updateNotification(1, 9)
            Handler().postDelayed({
                nav.updateNotification(1, 0)
                nav.activeAllTabs()
            }, 2000)
        }, 2000)

        nav.disableAllTabs()
    }
    /**
     * Example when creating Dynamic menu
     * @see Menu.add
     *
     */
    private fun menuInit() {
        val p  = PopupMenu(this, null)
        val dynamicMenu: Menu = p.menu
        dynamicMenu.add(Menu.NONE, Menu.NONE, 1, "One")?.setIcon(R.drawable.ic_launcher_background)
        dynamicMenu.add(Menu.NONE, Menu.NONE, 1, "Two")?.setIcon(R.drawable.ic_location)
        dynamicMenu.add(Menu.NONE, Menu.NONE,0, "Three")?.setIcon(R.drawable.bg_circle_notification)
        dynamicMenu.add(Menu.NONE, Menu.NONE, 1, "Four")?.setIcon(R.drawable.ic_profile)

        nav.setMenu(dynamicMenu)
    }
    override fun onClickNavigationItem(pos: Int) {
        Log.d(TAG, "Tab is clicked")
        Log.d(TAG, "Current position : " + nav.currentPosition)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        nav.onSaveInstanceStateView(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        nav.onRestoreInstanceStateView(savedInstanceState)
    }
}
