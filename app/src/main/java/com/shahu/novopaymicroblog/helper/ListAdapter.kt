package com.shahu.novopaymicroblog.helper

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shahu.novopaymicroblog.R
import com.shahu.novopaymicroblog.models.User
import java.util.ArrayList

/**
 * Created by Shahu Ronghe on 25, March, 2020
 * in Novopay Microblog
 */
class ListAdapter(private val list: ArrayList<Parcelable>?)
    : RecyclerView.Adapter<ListAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user: User = list?.get(position) as User
        holder.bind(user)
    }

    override fun getItemCount(): Int = list!!.size

    class UserViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.user_list_item, parent, false)) {
        private var mUserName: TextView? = null


        init {
            mUserName = itemView.findViewById(R.id.list_item_username)
        }

        fun bind(user: User) {
            mUserName?.text = user.name
        }

    }
}