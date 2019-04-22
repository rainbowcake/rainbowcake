@file:Suppress("NOTHING_TO_INLINE")

package co.zsmb.rainbowcake.extensions

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

/**
 * Returns the value associated with the given key.
 *
 * @throws IllegalArgumentException if the key does not exist.
 * @throws IllegalStateException if the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use the extension in the navigation package instead.",
        level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith("requireBoolean(key)", "co.zsmb.rainbowcake.navigation.extensions.requireBoolean")
)
inline fun Bundle.requireBoolean(key: String): Boolean = requireImpl(key)

/**
 * Returns the value associated with the given key, or null if the key doesn't exist,
 * or the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use the extension in the navigation package instead.",
        level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith("getBooleanOrNull(key)", "co.zsmb.rainbowcake.navigation.extensions.getBooleanOrNull")
)
inline fun Bundle.getBooleanOrNull(key: String): Boolean? = getOrNullImpl(key)

/**
 * Returns the value associated with the given key.
 *
 * @throws IllegalArgumentException if the key does not exist.
 * @throws IllegalStateException if the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use the extension in the navigation package instead.",
        level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith("requireInt(key)", "co.zsmb.rainbowcake.navigation.extensions.requireInt")
)
inline fun Bundle.requireInt(key: String): Int = requireImpl(key)

/**
 * Returns the value associated with the given key, or null if the key doesn't exist,
 * or the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use the extension in the navigation package instead.",
        level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith("getIntOrNull(key)", "co.zsmb.rainbowcake.navigation.extensions.getIntOrNull")
)
inline fun Bundle.getIntOrNull(key: String): Int? = getOrNullImpl(key)

/**
 * Returns the value associated with the given key.
 *
 * @throws IllegalArgumentException if the key does not exist.
 * @throws IllegalStateException if the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use the extension in the navigation package instead.",
        level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith("requireLong(key)", "co.zsmb.rainbowcake.navigation.extensions.requireLong")
)
inline fun Bundle.requireLong(key: String): Long = requireImpl(key)

/**
 * Returns the value associated with the given key, or null if the key doesn't exist,
 * or the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use the extension in the navigation package instead.",
        level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith("getLongOrNull(key)", "co.zsmb.rainbowcake.navigation.extensions.getLongOrNull")
)
inline fun Bundle.getLongOrNull(key: String): Long? = getOrNullImpl(key)

/**
 * Returns the value associated with the given key.
 *
 * @throws IllegalArgumentException if the key does not exist.
 * @throws IllegalStateException if the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use the extension in the navigation package instead.",
        level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith("requireString(key)", "co.zsmb.rainbowcake.navigation.extensions.requireString")
)
inline fun Bundle.requireString(key: String): String = requireImpl(key)

/**
 * Returns the value associated with the given key, or null if the key doesn't exist,
 * or the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use the extension in the navigation package instead.",
        level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith("getStringOrNull(key)", "co.zsmb.rainbowcake.navigation.extensions.getStringOrNull")
)
inline fun Bundle.getStringOrNull(key: String): String? = getOrNullImpl(key)

/**
 * Returns the value associated with the given key.
 *
 * @throws IllegalArgumentException if the key does not exist.
 * @throws IllegalStateException if the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use the extension in the navigation package instead.",
        level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith("requireParcelable(key)", "co.zsmb.rainbowcake.navigation.extensions.requireParcelable")
)
inline fun <reified T : Parcelable> Bundle.requireParcelable(key: String): T = requireImpl(key)

/**
 * Returns the value associated with the given key, or null if the key doesn't exist,
 * or the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use the extension in the navigation package instead.",
        level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith("getParcelableOrNull(key)", "co.zsmb.rainbowcake.navigation.extensions.getParcelableOrNull")
)
inline fun <reified T : Parcelable> Bundle.getParcelableOrNull(key: String): T? = getOrNullImpl(key)

/**
 * Returns the value associated with the given key.
 *
 * @throws IllegalArgumentException if the key does not exist.
 * @throws IllegalStateException if the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use the extension in the navigation package instead.",
        level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith("requireSerializable(key)", "co.zsmb.rainbowcake.navigation.extensions.requireSerializable")
)
inline fun <reified T : Serializable> Bundle.requireSerializable(key: String): T = requireImpl(key)

/**
 * Returns the value associated with the given key, or null if the key doesn't exist,
 * or the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use the extension in the navigation package instead.",
        level = DeprecationLevel.WARNING,
        replaceWith = ReplaceWith("getSerializableOrNull(key)", "co.zsmb.rainbowcake.navigation.extensions.getSerializableOrNull")
)
inline fun <reified T : Serializable> Bundle.getSerializableOrNull(key: String): T? = getOrNullImpl(key)


//region Impl
/**
 * Returns the value associated with the given key.
 *
 * @throws IllegalArgumentException if the key does not exist.
 * @throws IllegalStateException if the stored value is of the wrong type.
 */
@PublishedApi
internal inline fun <reified T : Any> Bundle.requireImpl(key: String): T {
    if (!containsKey(key)) {
        throw IllegalArgumentException("Bundle has no key $key")
    }
    return get(key) as? T ?: throw IllegalStateException("Wrong type found in Bundle for key $key")
}

/**
 * Returns the value associated with the given key, or null if the key doesn't exist,
 * or the stored value is of the wrong type.
 */
@PublishedApi
internal inline fun <reified T : Any> Bundle.getOrNullImpl(key: String): T? {
    if (!containsKey(key)) {
        return null
    }
    return get(key) as? T?
}
//endregion
