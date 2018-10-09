package hu.autsoft.rainbowcake.base

/**
 * This is simply a marker interface so that classes that represent events
 * to be dispatched from a ViewModel are clearly identifiable and that the
 * associated methods don't deal with the very broad [Any] type.
 */
interface OneShotEvent
