@file:Suppress("NOTHING_TO_INLINE")

package hu.autsoft.rainbowcake.extensions

import android.os.Bundle

/**
 * Returns the value associated with the given key.
 *
 * @throws IllegalStateException if the key does not exist
 */
inline fun Bundle.requireLong(key: String): Long {
    return if (containsKey(key)) getLong(key) else throw IllegalStateException("Bundle has no key $key")
}

/**
 * Returns the value associated with the given key, or null if the key doesn't exist.
 */
inline fun Bundle.getLongOrNull(key: String): Long? {
    return if (containsKey(key)) getLong(key) else null
}

/**
 * Returns the value associated with the given key.
 *
 * @throws IllegalStateException if the key does not exist
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
inline fun Bundle.requireString(key: String): String {
    return if (containsKey(key)) getString(key) else throw IllegalStateException("Bundle has no key $key")
}

/**
 * Returns the value associated with the given key, or null if the key doesn't exist.
 */
inline fun Bundle.getStringOrNull(key: String): String? {
    return if (containsKey(key)) getString(key) else null
}

/**
 * Returns the value associated with the given key.
 *
 * @throws IllegalStateException if the key does not exist
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
inline fun Bundle.requireBoolean(key: String): Boolean {
    return if (containsKey(key)) getBoolean(key) else throw IllegalStateException("Bundle has no key $key")
}

/**
 * Returns the value associated with the given key, or null if the key doesn't exist.
 */
inline fun Bundle.getBooleanOrNull(key: String): Boolean? {
    return if (containsKey(key)) getBoolean(key) else null
}
