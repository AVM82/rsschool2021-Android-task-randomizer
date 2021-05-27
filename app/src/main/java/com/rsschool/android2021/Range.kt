package com.rsschool.android2021

import android.os.Parcel
import android.os.Parcelable

data class Range(var min: Int = 0, var max: Int = 0) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(min)
        parcel.writeInt(max)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Range> {
        override fun createFromParcel(parcel: Parcel): Range {
            return Range(parcel)
        }

        override fun newArray(size: Int): Array<Range?> {
            return arrayOfNulls(size)
        }
    }
}
