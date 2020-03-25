package com.shahu.novopaymicroblog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shahu.novopaymicroblog.R
import com.shahu.novopaymicroblog.models.Post
import java.util.*

/**
 * Created by Shahu Ronghe on 25, March, 2020
 * in Novopay Microblog
 */
class PostListAdapter(
    private val list: ArrayList<Post>?,
    private val clickListener: (Post) -> Unit
) :
    RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post: Post = list?.get(position) as Post
        holder.bind(post, clickListener)
    }

    override fun getItemCount(): Int = list!!.size

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.post_list_item, parent, false)) {
        private var mPostTitle: TextView? = null

        init {
            mPostTitle = itemView.findViewById(R.id.tv_name)
        }

        fun bind(post: Post, clickListener: (Post) -> Unit) {
            mPostTitle?.text = post.title
            itemView.setOnClickListener { clickListener(post) }
        }

    }
}