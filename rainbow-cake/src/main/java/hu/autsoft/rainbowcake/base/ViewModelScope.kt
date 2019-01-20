package hu.autsoft.rainbowcake.base


sealed class ViewModelScope {

    object Default : ViewModelScope()

    object ParentFragment : ViewModelScope()

    data class Activity(val key: String) : ViewModelScope()

}
