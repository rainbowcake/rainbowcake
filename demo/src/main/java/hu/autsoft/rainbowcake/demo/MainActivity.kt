package hu.autsoft.rainbowcake.demo

import android.os.Bundle
import hu.autsoft.rainbowcake.demo.ui.example.ExampleFragment
import hu.autsoft.rainbowcake.navigation.SimpleNavActivity

class MainActivity : SimpleNavActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            navigator.add(ExampleFragment())
        }
    }

}
