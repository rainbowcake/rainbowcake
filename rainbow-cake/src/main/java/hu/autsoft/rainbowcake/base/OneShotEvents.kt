package hu.autsoft.rainbowcake.base

/**
 * This is simply a marker interface so that classes that represent events
 * to be dispatched from a ViewModel are clearly identifiable and that the
 * associated methods don't deal with the very broad [Any] type.
 */
interface OneShotEvent

/**
 * An additional marker interface for a more specific kind of [OneShotEvent].
 * Events implementing this interface can not only be dispatched immediately,
 * but may also be queued if the given Fragment or Activity is not currently
 * active. See [postQueuedEvent][BaseViewModel.postQueuedEvent] and
 * [queuedEvents][BaseViewModel.queuedEvents].
 *
 */
interface QueuedOneShotEvent : OneShotEvent
