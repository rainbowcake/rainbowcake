package hu.autsoft.rainbowcake.navigation;

import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import hu.autsoft.rainbowcake.base.BaseFragment;

class AccessibilityHack {

    private AccessibilityHack() {
        // Non-instantiable class
    }

    @SuppressWarnings({"KotlinInternalInJava", "deprecation"})
    static void setOverrideAnimation(
            @NonNull final BaseFragment fragment,
            @AnimRes @Nullable final Integer overrideAnimation) {
        fragment.setOverrideAnimation$rainbow_cake_debug(overrideAnimation);
    }

}
