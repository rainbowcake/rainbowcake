package co.zsmb.rainbowcake.extensions

/**
 * A dummy extension to use on a `when` statement to make it an expression
 * and guarantee that its branches perform exhaustive checks.
 *
 * Usage example:
 *
 * ```kotlin
 * when (viewState: ViewState) {
 *     is Loading -> { /* Show loading */ }
 *     is Errored -> { /* Show error */ }
 *     is Ready -> { /* Show ready */ }
 * }.exhaustive
 * ```
 */
val <T> T.exhaustive: T
    get() = this
