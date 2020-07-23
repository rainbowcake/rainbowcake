package co.zsmb.rainbowcake.demo.ui.sharedvmpager

import android.os.Bundle
import android.view.View
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.demo.R
import kotlinx.android.synthetic.main.fragment_shared_vmpager.*

class SharedVMPagerFragment : RainbowCakeFragment<SharedVMPagerViewState, SharedVMPagerViewModel>() {

    override fun getViewResource() = R.layout.fragment_shared_vmpager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        provideViewModel(getViewModelFromFactory())
    }

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
