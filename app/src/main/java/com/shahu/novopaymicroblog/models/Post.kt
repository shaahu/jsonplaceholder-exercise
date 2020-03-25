package com.shahu.novopaymicroblog.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Shahu Ronghe on 25, March, 2020
 * in Novopay Microblog
 */
class Post() : Parcelable {
    var userId = 0
    var id = 0
    var title: String? = null
    var body: String? = null

    constructor(parcel: Parcel) : this() {
        userId = parcel.readInt()
        id = parcel.readInt()
        title = parcel.readString()
        body = parcel.readString()
    }


    constructor(userId: Int, id: Int, title: String?, body: String?) : this() {
        this.userId = userId
        this.id = id
        this.title = title
        this.body = body
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(userId)
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }
}