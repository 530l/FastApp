

package com.fastapp.entity

import android.os.Parcel
import android.os.Parcelable

/** 使用Android自带的[Parcelable]进行序列化传递 */
// @kotlinx.parcelize.Parcelize // 请不要使用注解而是手动实现
data class ParcelableModel(var name: String = "ModelParcelable") : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readString() ?: "ModelParcelable") // 读取空则赋值默认值

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParcelableModel> {
        override fun createFromParcel(parcel: Parcel): ParcelableModel {
            return ParcelableModel(parcel)
        }

        override fun newArray(size: Int): Array<ParcelableModel?> {
            return arrayOfNulls(size)
        }
    }
}