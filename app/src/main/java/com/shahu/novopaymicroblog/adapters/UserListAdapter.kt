package com.shahu.novopaymicroblog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shahu.novopaymicroblog.R
import com.shahu.novopaymicroblog.models.User
import java.util.*

/**
 * Created by Shahu Ronghe on 25, March, 2020
 * in Novopay Microblog
 */
class UserListAdapter(
    private val list: ArrayList<User>?,
    private val clickListener: (User) -> Unit
) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: User = list?.get(position) as User
        holder.bind(user, clickListener)
    }

    override fun getItemCount(): Int = list!!.size

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.user_list_item, parent, false)) {
        private var mUserName: TextView? = null


        init {
            mUserName = itemView.findViewById(R.id.tv_name)
        }

        fun bind(user: User, clickListener: (User) -> Unit) {
            mUserName?.text = user.name
            itemView.setOnClickListener { clickListener(user) }
        }

    }
}