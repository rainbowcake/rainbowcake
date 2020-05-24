package co.zsmb.rainbowcake.demo.ui.sharedvmpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import co.zsmb.rainbowcake.demo.ui.sharedvmpager.pages.ScreenOneFragment
import co.zsmb.rainbowcake.demo.ui.sharedvmpager.pages.ScreenThreeFragment
import co.zsmb.rainbowcake.demo.ui.sharedvmpager.pages.ScreenTwoFragment

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
