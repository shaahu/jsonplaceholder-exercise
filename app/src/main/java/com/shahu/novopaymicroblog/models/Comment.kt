package com.shahu.novopaymicroblog.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Shahu Ronghe on 25, March, 2020
 * in Novopay Microblog
 */
class Comment() : Parcelable {
    var postId = 0
    var id = 0
    var name: String? = null
    var email: String? = null
    var body: String? = null

    constructor(parcel: Parcel) : this() {
        postId = parcel.readInt()
        id = parcel.readInt()
        name = parcel.readString()
        email = parcel.readString()
        body = parcel.readString()
    }


    constructor(postId: Int, id: Int, name: String?, email: String?, body: String?) : this() {
        this.postId = postId
        this.id = id
        this.name = name
        this.email = email
        this.body = body
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(postId)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Comment> {
        override fun createFromParcel(parcel: Parcel): Comment {
            return Comment(parcel)
        }

        override fun newArray(size: Int): Array<Comment?> {
            return arrayOfNulls(size)
        }
    }

}