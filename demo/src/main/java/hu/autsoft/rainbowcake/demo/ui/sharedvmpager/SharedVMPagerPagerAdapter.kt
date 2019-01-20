package hu.autsoft.rainbowcake.demo.ui.sharedvmpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import hu.autsoft.rainbowcake.demo.ui.sharedvmpager.pages.ScreenOneFragment
import hu.autsoft.rainbowcake.demo.ui.sharedvmpager.pages.ScreenThreeFragment
import hu.autsoft.rainbowcake.demo.ui.sharedvmpager.pages.ScreenTwoFragment

class SharedVMPagerPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    class Page(
            val title: String,
            val fragment: Fragment
    )

    val pages = listOf(
            Page("One", ScreenOneFragment()),
            Page("Two", ScreenTwoFragment()),
            Page("Three", ScreenThreeFragment())
    )

    override fun getItem(position: Int): Fragment {
        return pages[position].fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pages[position].title
    }

    override fun getCount(): Int {
        return pages.size
    }

}
