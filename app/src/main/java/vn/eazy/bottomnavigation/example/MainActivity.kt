package vn.eazy.bottomnavigation.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import harryle.eazy.vn.bottomnavigation.navigation.BottomNavigationAdapter
import harryle.eazy.vn.bottomnavigation.navigation.OnNavigationListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnNavigationListener {
    companion object {
        val TAG: String = MainActivity.javaClass.simpleName
    }

    private val adapter: BottomNavigationAdapter by lazy { BottomNavigationAdapter(supportFragmentManager) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav.setMenu(R.menu.nav)

        adapter.addFragment(TestFragment.newInstance())
        adapter.addFragment(TestFragment.newInstance())
        adapter.addFragment(TestFragment.newInstance())
        adapter.addFragment(TestFragment.newInstance())

        vpMain.setPagingEnabled(true)
        vpMain.adapter = adapter
        nav.setupWithViewPager(vpMain)
        nav.setOnNavigationListener(this)

        nav.show()
        Log.d(TAG,"Current position : " + nav.currentPosition)

//        //Todo don't do like this (it's just a example)
//        Handler().postDelayed({
//            nav.hide()
//            Handler().postDelayed({
//                nav.show()
//            }, 2000)
//        }, 2000)
    }

    override fun onClickNavigationItem(pos: Int) {
        Log.d(TAG, "Tab is clicked")
        Log.d(TAG,"Current position : " + nav.currentPosition)
    }
}
