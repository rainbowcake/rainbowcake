package hu.autsoft.rainbowcake.demo.ui.sharedvmpager

import android.os.Bundle
import android.view.View
import hu.autsoft.rainbowcake.base.RainbowCakeFragment
import hu.autsoft.rainbowcake.base.getViewModelFromFactory
import hu.autsoft.rainbowcake.demo.R
import kotlinx.android.synthetic.main.fragment_shared_vmpager.*

class SharedVMPagerFragment : RainbowCakeFragment<SharedVMPagerViewState, SharedVMPagerViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_shared_vmpager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = SharedVMPagerPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onStart() {
        super.onStart()

        viewModel.load()
    }

    override fun render(viewState: SharedVMPagerViewState) {
        // TODO Render state
    }

}
