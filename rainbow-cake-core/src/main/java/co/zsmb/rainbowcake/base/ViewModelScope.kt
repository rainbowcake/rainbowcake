package co.zsmb.rainbowcake.base


/**
 * Represents various scopes that ViewModel instances may exist in. Note that
 * these ViewModel scopes only exist in terms of Fragment ViewModels, as
 * Activity ViewModels are always scoped to their Activity.
 */
sealed class ViewModelScope {

    /**
     * A ViewModel with this scope is scoped to its Fragment.
     *
     * A new ViewModel will be created each time a Fragment using this scope
     * is instantiated, and it will be cleared when the Fragment is destroyed
     * (ignoring the Fragment being recreated on configuration changes).
     *
     * This is the default behaviour of ViewModels.
     */
    object Default : ViewModelScope()

    /**
     * A ViewModel with this scope is scoped to the parent of its Fragment.
     *
     * A parent Fragment MUST exist in this case, otherwise instantiation will
     * fail. ViewModels in this scope will be cleared when the parent Fragment
     * is destroyed.
     *
     * The typical use case for this scope is multiple nested Fragments that
     * need to share data amongst themselves through a ViewModel, e.g. those
     * in a ViewPager.
     *
     * A [key] may be provided to scope multiple separate instances of the
     * same ViewModel class within the same parent Fragment. If no [key] is
     * provided, there will only be a single instance of the ViewModel within
     * the scope of the parent.
     *
     * @param key The key for this ViewModel, used as described above.
     */
    sealed class ParentFragment(val key: String? = null) : ViewModelScope() {
        internal class KeyedParentFragment(key: String) : ParentFragment(key)

        companion object : ParentFragment() {
            operator fun invoke(key: String): ViewModelScope {
                return KeyedParentFragment(key)
            }
        }
    }

    /**
     * A ViewModel with this scope is scoped to the current Activity.
     *
     * ViewModels in this scope will only be cleared when this Activity is
     * destroyed, meaning they might exist long after any Fragments using
     * them have been destroyed.
     *
     * A [key] may be provided to scope multiple separate instances of the
     * same ViewModel class within the same Activity. If no [key] is provided,
     * the ViewModel will essentially exist as a Singleton within the Activity.
     *
     * @param key The key for this ViewModel, used as described above.
     */
    sealed class Activity(val key: String? = null) : ViewModelScope() {
        internal class KeyedActivity(key: String) : Activity(key)

        companion object : Activity() {
            operator fun invoke(key: String): ViewModelScope {
                return KeyedActivity(key)
            }
        }
    }

}
