package com.shahu.novopaymicroblog.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Shahu Ronghe on 24, March, 2020
 * in Novopay Microblog
 */
class User() : Parcelable {
    var id = 0
    var name: String? = null
    var email: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString()
        email = parcel.readString()
    }

    constructor(id: Int, name: String?, email: String?) : this() {
        this.id = id
        this.name = name
        this.email = email
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}