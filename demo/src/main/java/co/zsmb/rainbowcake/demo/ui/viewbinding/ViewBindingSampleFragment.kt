package co.zsmb.rainbowcake.demo.ui.viewbinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.demo.databinding.FragmentViewBindingBinding

class ViewBindingSampleFragment : RainbowCakeFragment<ViewBindingSampleState, ViewBindingSampleViewModel>() {

    private var _binding: FragmentViewBindingBinding? = null
    private val binding get() = _binding!!

    override fun provideViewModel(): ViewBindingSampleViewModel = getViewModelFromFactory()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentViewBindingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.load()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun render(viewState: ViewBindingSampleState) {
        binding.messageText.text = viewState.message
    }
}
