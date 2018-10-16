package hu.autsoft.rainbowcake.demo.ui.example


sealed class ExampleViewState

class DataState(val data: String = "") : ExampleViewState()

object NoDataState : ExampleViewState()
