package co.zsmb.rainbowcake.navigation.extensions

import android.location.Location
import android.net.Uri
import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Calendar
import java.util.UUID

@RunWith(AndroidJUnit4::class)
internal class BundleTest {

    //region Boolean
    @Test
    fun testBoolean() {
        val bundle = Bundle().apply {
            putBoolean("key1", true)
            putBoolean("key2", false)
        }

        assertEquals(true, bundle.requireBoolean("key1"))
        assertEquals(false, bundle.requireBoolean("key2"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testBooleanBadKey() {
        val bundle = Bundle().apply {
            putBoolean("key", true)
        }

        bundle.requireBoolean("bad_key")
    }

    @Test(expected = IllegalStateException::class)
    fun testBooleanBadType() {
        val bundle = Bundle().apply {
            putString("key", "hello")
        }

        bundle.requireBoolean("key")
    }

    @Test
    fun testNullableBoolean() {
        val bundle = Bundle().apply {
            putBoolean("key", true)
            putInt("other_key", 215)
        }

        assertEquals(true, bundle.getBooleanOrNull("key"))
        assertNull(bundle.getBooleanOrNull("other_key"))
        assertNull(bundle.getBooleanOrNull("bad_key"))
    }
    //endregion

    //region Int
    @Test
    fun testInt() {
        val bundle = Bundle().apply {
            putInt("key", 25)
        }

        assertEquals(25, bundle.requireInt("key"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testIntBadKey() {
        val bundle = Bundle().apply {
            putInt("key", 25)
        }

        bundle.requireInt("bad_key")
    }

    @Test(expected = IllegalStateException::class)
    fun testIntBadType() {
        val bundle = Bundle().apply {
            putLong("key", 25L)
        }

        bundle.requireInt("key")
    }

    @Test
    fun testNullableInt() {
        val bundle = Bundle().apply {
            putInt("key", 25)
            putLong("other_key", 25L)
        }

        assertEquals(25, bundle.getIntOrNull("key"))
        assertNull(bundle.getIntOrNull("other_key"))
        assertNull(bundle.getIntOrNull("bad_key"))
    }
    //endregion

    //region Long
    @Test
    fun testLong() {
        val bundle = Bundle().apply {
            putLong("key", 25L)
        }

        assertEquals(25, bundle.requireLong("key"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testLongBadKey() {
        val bundle = Bundle().apply {
            putLong("key", 25L)
        }

        bundle.requireLong("bad_key")
    }

    @Test(expected = IllegalStateException::class)
    fun testLongBadType() {
        val bundle = Bundle().apply {
            putInt("key", 12)
        }

        bundle.requireLong("key")
    }

    @Test
    fun testNullableLong() {
        val bundle = Bundle().apply {
            putLong("key", 25L)
            putInt("other_key", 25)
        }

        assertEquals(25L, bundle.getLongOrNull("key"))
        assertNull(bundle.getLongOrNull("other_key"))
        assertNull(bundle.getLongOrNull("bad_key"))
    }
    //endregion

    //region String
    @Test
    fun testString() {
        val bundle = Bundle().apply {
            putString("key", "hello")
        }

        assertEquals("hello", bundle.requireString("key"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testStringBadKey() {
        val bundle = Bundle().apply {
            putString("key", "hello")
        }

        bundle.requireString("bad_key")
    }

    @Test(expected = IllegalStateException::class)
    fun testStringBadType() {
        val bundle = Bundle().apply {
            putInt("key", 90210)
        }

        bundle.requireLong("key")
    }

    @Test
    fun testNullableString() {
        val bundle = Bundle().apply {
            putString("key", "hello")
            putInt("other_key", 25)
        }

        assertEquals("hello", bundle.getStringOrNull("key"))
        assertNull(bundle.getStringOrNull("other_key"))
        assertNull(bundle.getStringOrNull("bad_key"))
    }
    //endregion

    //region Parcelable
    private val sampleUri: Uri
        get() = Uri.parse("some/fake/path")

    private val sampleLocation: Location
        get() {
            return Location("dummy").apply {
                latitude = 25.832466
                longitude = -70.980690
            }
        }

    @Test
    fun testParcelable() {
        val bundle = Bundle().apply {
            putParcelable("key", sampleUri)
        }

        assertEquals(sampleUri, bundle.requireParcelable<Uri>("key"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testParcelableBadKey() {
        val bundle = Bundle().apply {
            putParcelable("key", sampleUri)
        }

        bundle.requireParcelable<Uri>("bad_key")
    }

    @Test(expected = IllegalStateException::class)
    fun testParcelableBadType() {
        val bundle = Bundle().apply {
            putParcelable("key", sampleLocation)
        }

        bundle.requireParcelable<Uri>("key")
    }

    @Test
    fun testNullableParcelable() {
        val bundle = Bundle().apply {
            putParcelable("key", sampleUri)
            putParcelable("other_key", sampleLocation)
            putLong("other_other_key", 25L)
        }

        assertEquals(sampleUri, bundle.getParcelableOrNull<Uri>("key"))
        assertNull(bundle.getParcelableOrNull<Uri>("other_key"))
        assertNull(bundle.getParcelableOrNull<Uri>("other_other_key"))
        assertNull(bundle.getParcelableOrNull<Uri>("bad_key"))
    }
    //endregion

    //region Serializable
    private val sampleUuid: UUID
        get() = UUID.fromString("1f3b3e0b-bc62-4606-bb4c-dbf768073912")

    private val sampleCalendar: Calendar
        get() = Calendar.getInstance().apply { timeInMillis = 123456789L }

    @Test
    fun testSerializable() {
        val bundle = Bundle().apply {
            putSerializable("key", sampleUuid)
        }

        assertEquals(sampleUuid, bundle.requireSerializable<UUID>("key"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testSerializableBadKey() {
        val bundle = Bundle().apply {
            putSerializable("key", sampleUuid)
        }

        bundle.requireSerializable<UUID>("bad_key")
    }

    @Test(expected = IllegalStateException::class)
    fun testSerializableBadType() {
        val bundle = Bundle().apply {
            putSerializable("key", sampleCalendar)
        }

        bundle.requireSerializable<UUID>("key")
    }

    @Test
    fun testNullableSerializable() {
        val bundle = Bundle().apply {
            putSerializable("key", sampleUuid)
            putSerializable("other_key", sampleCalendar)
            putLong("other_other_key", 25L)
        }

        assertEquals(sampleUuid, bundle.getSerializableOrNull<UUID>("key"))
        assertNull(bundle.getSerializableOrNull<UUID>("other_key"))
        assertNull(bundle.getSerializableOrNull<UUID>("other_other_key"))
        assertNull(bundle.getSerializableOrNull<UUID>("bad_key"))
    }
    //endregion

}
