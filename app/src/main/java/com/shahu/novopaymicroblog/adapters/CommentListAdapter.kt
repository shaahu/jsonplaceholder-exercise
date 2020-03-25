package com.shahu.novopaymicroblog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shahu.novopaymicroblog.R
import com.shahu.novopaymicroblog.models.Comment
import java.util.*

/**
 * Created by Shahu Ronghe on 25, March, 2020
 * in Novopay Microblog
 */
class CommentListAdapter(
    private val list: ArrayList<Comment>?
) :
    RecyclerView.Adapter<CommentListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment: Comment = list?.get(position) as Comment
        holder.bind(comment)
    }

    override fun getItemCount(): Int = list!!.size

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.post_list_item, parent, false)) {
        private var mCommentTv: TextView? = null

        init {
            mCommentTv = itemView.findViewById(R.id.tv_name)
        }

        fun bind(comment: Comment) {
            val text = getAppendedComment(comment)
            mCommentTv?.text = text
        }

        private fun getAppendedComment(comment: Comment): String {
            val id = comment.id
            val name = comment.name
            val email = comment.email
            val body = comment.body
            return "Id: $id\nComment Name: $name\nCommenter Email: $email\nComment Body\n$body"
        }

    }
}