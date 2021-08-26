package co.zsmb.rainbowcake.hiltdemo.blank

sealed class BlankViewState

object Initial : BlankViewState()

data class BlankReady(val data: String = "") : BlankViewState()