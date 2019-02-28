package com.ekoebtech.tutorforme.Model

import android.os.Parcel
import android.os.Parcelable

data class ClassListModel(val className : String, val classId : String) : Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(className);
        dest?.writeString(classId);

    }

    override fun describeContents(): Int {
       return 0
    }

    override fun toString(): String {
        return className
    }

    companion object CREATOR : Parcelable.Creator<ClassListModel> {
        override fun createFromParcel(parcel: Parcel): ClassListModel {
            return ClassListModel(parcel)
        }

        override fun newArray(size: Int): Array<ClassListModel?> {
            return arrayOfNulls(size)
        }
    }
}