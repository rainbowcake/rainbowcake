package co.zsmb.rainbowcake.demo

import android.os.Bundle
import co.zsmb.rainbowcake.demo.ui.example.ExampleFragment
import co.zsmb.rainbowcake.navigation.SimpleNavActivity

class MainActivity : SimpleNavActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            navigator.add(ExampleFragment())
        }
    }

}
