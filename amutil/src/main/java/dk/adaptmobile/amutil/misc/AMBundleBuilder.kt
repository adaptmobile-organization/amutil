package dk.adaptmobile.amutil.misc

/**
 * Created by Thomas on 05/05/2017.
 */

import android.os.Bundle
import android.os.Parcelable
import android.util.SparseArray
import java.io.Serializable
import java.util.*

class AMBundleBuilder(private val bundle: Bundle) {

    fun putAll(bundle: Bundle): AMBundleBuilder {
        this.bundle.putAll(bundle)
        return this
    }

    fun putBoolean(key: String, value: Boolean): AMBundleBuilder {
        bundle.putBoolean(key, value)
        return this
    }

    fun putBooleanArray(key: String, value: BooleanArray): AMBundleBuilder {
        bundle.putBooleanArray(key, value)
        return this
    }

    fun putDouble(key: String, value: Double): AMBundleBuilder {
        bundle.putDouble(key, value)
        return this
    }

    fun putDoubleArray(key: String, value: DoubleArray): AMBundleBuilder {
        bundle.putDoubleArray(key, value)
        return this
    }

    fun putLong(key: String, value: Long): AMBundleBuilder {
        bundle.putLong(key, value)
        return this
    }

    fun putLongArray(key: String, value: LongArray): AMBundleBuilder {
        bundle.putLongArray(key, value)
        return this
    }

    fun putString(key: String, value: String): AMBundleBuilder {
        bundle.putString(key, value)
        return this
    }

    fun putStringArray(key: String, value: Array<String>): AMBundleBuilder {
        bundle.putStringArray(key, value)
        return this
    }

    fun putBundle(key: String, value: Bundle): AMBundleBuilder {
        bundle.putBundle(key, value)
        return this
    }

    fun putByte(key: String, value: Byte): AMBundleBuilder {
        bundle.putByte(key, value)
        return this
    }

    fun putByteArray(key: String, value: ByteArray): AMBundleBuilder {
        bundle.putByteArray(key, value)
        return this
    }

    fun putChar(key: String, value: Char): AMBundleBuilder {
        bundle.putChar(key, value)
        return this
    }

    fun putCharArray(key: String, value: CharArray): AMBundleBuilder {
        bundle.putCharArray(key, value)
        return this
    }

    fun putCharSequence(key: String, value: CharSequence): AMBundleBuilder {
        bundle.putCharSequence(key, value)
        return this
    }

    fun putCharSequenceArray(key: String, value: Array<CharSequence>): AMBundleBuilder {
        bundle.putCharSequenceArray(key, value)
        return this
    }

    fun putCharSequenceArrayList(key: String, value: ArrayList<CharSequence>): AMBundleBuilder {
        bundle.putCharSequenceArrayList(key, value)
        return this
    }

    fun putInt(key: String, value: Int): AMBundleBuilder {
        bundle.putInt(key, value)
        return this
    }

    fun putIntArray(key: String, value: IntArray): AMBundleBuilder {
        bundle.putIntArray(key, value)
        return this
    }

    fun putFloat(key: String, value: Float): AMBundleBuilder {
        bundle.putFloat(key, value)
        return this
    }

    fun putFloatArray(key: String, value: FloatArray): AMBundleBuilder {
        bundle.putFloatArray(key, value)
        return this
    }

    fun putIntegerArrayList(key: String, value: ArrayList<Int>): AMBundleBuilder {
        bundle.putIntegerArrayList(key, value)
        return this
    }

    fun putParcelable(key: String, value: Parcelable): AMBundleBuilder {
        bundle.putParcelable(key, value)
        return this
    }

    fun putParcelableArray(key: String, value: Array<Parcelable>): AMBundleBuilder {
        bundle.putParcelableArray(key, value)
        return this
    }

    fun putParcelableArrayList(key: String, value: ArrayList<out Parcelable>): AMBundleBuilder {
        bundle.putParcelableArrayList(key, value)
        return this
    }

    fun putSerializable(key: String, value: Serializable): AMBundleBuilder {
        bundle.putSerializable(key, value)
        return this
    }

    fun putShort(key: String, value: Short): AMBundleBuilder {
        bundle.putShort(key, value)
        return this
    }

    fun putShortArray(key: String, value: ShortArray): AMBundleBuilder {
        bundle.putShortArray(key, value)
        return this
    }

    fun putSparseParcelableArray(key: String, value: SparseArray<out Parcelable>): AMBundleBuilder {
        bundle.putSparseParcelableArray(key, value)
        return this
    }

    fun putStringArrayList(key: String, value: ArrayList<String>): AMBundleBuilder {
        bundle.putStringArrayList(key, value)
        return this
    }

    fun build(): Bundle {
        return bundle
    }

}