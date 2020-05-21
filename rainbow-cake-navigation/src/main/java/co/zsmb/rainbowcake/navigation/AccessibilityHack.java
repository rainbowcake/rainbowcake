package co.zsmb.rainbowcake.navigation;

import androidx.annotation.AnimRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import co.zsmb.rainbowcake.base.RainbowCakeFragment;

class AccessibilityHack {

    private AccessibilityHack() {
        // Non-instantiable class
    }

    @SuppressWarnings({"KotlinInternalInJava"})
    static void setOverrideAnimation(
            @NonNull final RainbowCakeFragment fragment,
            @AnimRes @Nullable final Integer overrideAnimation) {
        fragment.overrideAnimation(overrideAnimation);
    }

}
