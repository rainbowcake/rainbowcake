package co.zsmb.rainbowcake.hiltdemo

import android.os.Bundle
import co.zsmb.rainbowcake.hiltdemo.blank.BlankFragment
import co.zsmb.rainbowcake.navigation.SimpleNavActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : SimpleNavActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            navigator.add(BlankFragment())
        }
    }
}