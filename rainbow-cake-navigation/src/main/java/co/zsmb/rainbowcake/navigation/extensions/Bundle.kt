@file:Suppress("NOTHING_TO_INLINE")

package co.zsmb.rainbowcake.navigation.extensions

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
        message = "Use extension from requireKTX instead",
        replaceWith = ReplaceWith(
                "this.requireBoolean(key)",
                "co.zsmb.requirektx.requireBoolean",
        ),
        level = DeprecationLevel.WARNING,
)
public inline fun Bundle.requireBoolean(key: String): Boolean = requireImpl(key)

/**
 * Returns the value associated with the given key, or null if the key doesn't exist,
 * or the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use extension from requireKTX instead",
        replaceWith = ReplaceWith(
                "this.getBooleanOrNull(key)",
                "co.zsmb.requirektx.getBooleanOrNull",
        ),
        level = DeprecationLevel.WARNING,
)
public inline fun Bundle.getBooleanOrNull(key: String): Boolean? = getOrNullImpl(key)

/**
 * Returns the value associated with the given key.
 *
 * @throws IllegalArgumentException if the key does not exist.
 * @throws IllegalStateException if the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use extension from requireKTX instead",
        replaceWith = ReplaceWith(
                "this.requireInt(key)",
                "co.zsmb.requirektx.requireInt",
        ),
        level = DeprecationLevel.WARNING,
)
public inline fun Bundle.requireInt(key: String): Int = requireImpl(key)

/**
 * Returns the value associated with the given key, or null if the key doesn't exist,
 * or the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use extension from requireKTX instead",
        replaceWith = ReplaceWith(
                "this.getIntOrNull(key)",
                "co.zsmb.requirektx.getIntOrNull",
        ),
        level = DeprecationLevel.WARNING,
)
public inline fun Bundle.getIntOrNull(key: String): Int? = getOrNullImpl(key)

/**
 * Returns the value associated with the given key.
 *
 * @throws IllegalArgumentException if the key does not exist.
 * @throws IllegalStateException if the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use extension from requireKTX instead",
        replaceWith = ReplaceWith(
                "this.requireLong(key)",
                "co.zsmb.requirektx.requireLong",
        ),
        level = DeprecationLevel.WARNING,
)
public inline fun Bundle.requireLong(key: String): Long = requireImpl(key)

/**
 * Returns the value associated with the given key, or null if the key doesn't exist,
 * or the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use extension from requireKTX instead",
        replaceWith = ReplaceWith(
                "this.getLongOrNull(key)",
                "co.zsmb.requirektx.getLongOrNull",
        ),
        level = DeprecationLevel.WARNING,
)
public inline fun Bundle.getLongOrNull(key: String): Long? = getOrNullImpl(key)

/**
 * Returns the value associated with the given key.
 *
 * @throws IllegalArgumentException if the key does not exist.
 * @throws IllegalStateException if the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use extension from requireKTX instead",
        replaceWith = ReplaceWith(
                "this.requireString(key)",
                "co.zsmb.requirektx.requireString",
        ),
        level = DeprecationLevel.WARNING,
)
public inline fun Bundle.requireString(key: String): String = requireImpl(key)

/**
 * Returns the value associated with the given key, or null if the key doesn't exist,
 * or the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use extension from requireKTX instead",
        replaceWith = ReplaceWith(
                "this.getStringOrNull(key)",
                "co.zsmb.requirektx.getStringOrNull",
        ),
        level = DeprecationLevel.WARNING,
)
public inline fun Bundle.getStringOrNull(key: String): String? = getOrNullImpl(key)

/**
 * Returns the value associated with the given key.
 *
 * @throws IllegalArgumentException if the key does not exist.
 * @throws IllegalStateException if the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use extension from requireKTX instead",
        replaceWith = ReplaceWith(
                "this.requireParcelable(key)",
                "co.zsmb.requirektx.requireParcelable",
        ),
        level = DeprecationLevel.WARNING,
)
public inline fun <reified T : Parcelable> Bundle.requireParcelable(key: String): T = requireImpl(key)

/**
 * Returns the value associated with the given key, or null if the key doesn't exist,
 * or the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use extension from requireKTX instead",
        replaceWith = ReplaceWith(
                "this.getParcelableOrNull(key)",
                "co.zsmb.requirektx.getParcelableOrNull",
        ),
        level = DeprecationLevel.WARNING,
)
public inline fun <reified T : Parcelable> Bundle.getParcelableOrNull(key: String): T? = getOrNullImpl(key)

/**
 * Returns the value associated with the given key.
 *
 * @throws IllegalArgumentException if the key does not exist.
 * @throws IllegalStateException if the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use extension from requireKTX instead",
        replaceWith = ReplaceWith(
                "this.requireSerializable(key)",
                "co.zsmb.requirektx.requireSerializable",
        ),
        level = DeprecationLevel.WARNING,
)
public inline fun <reified T : Serializable> Bundle.requireSerializable(key: String): T = requireImpl(key)

/**
 * Returns the value associated with the given key, or null if the key doesn't exist,
 * or the stored value is of the wrong type.
 */
@Deprecated(
        message = "Use extension from requireKTX instead",
        replaceWith = ReplaceWith(
                "this.getSerializableOrNull(key)",
                "co.zsmb.requirektx.getSerializableOrNull",
        ),
        level = DeprecationLevel.WARNING,
)
public inline fun <reified T : Serializable> Bundle.getSerializableOrNull(key: String): T? = getOrNullImpl(key)


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
