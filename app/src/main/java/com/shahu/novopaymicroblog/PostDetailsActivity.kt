package com.shahu.novopaymicroblog

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.shahu.novopaymicroblog.adapters.CommentListAdapter
import com.shahu.novopaymicroblog.extractor.ObjectExtractor
import com.shahu.novopaymicroblog.helper.Constants
import com.shahu.novopaymicroblog.models.Comment
import com.shahu.novopaymicroblog.models.Post
import kotlinx.android.synthetic.main.activity_post_details.*
import kotlinx.android.synthetic.main.activity_user_details.tv_user_email
import kotlinx.android.synthetic.main.activity_user_details.tv_user_id
import kotlinx.android.synthetic.main.activity_user_details.tv_username
import org.json.JSONArray
import org.json.JSONObject

class PostDetailsActivity : AppCompatActivity() {

    private var mPost: Post? = null
    private lateinit var mRequestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        mRequestQueue = Volley.newRequestQueue(this)
        mPost = intent.getParcelableExtra("post")
        if (mPost != null) {
            inflateView()
        }
    }

    private fun inflateView() {
        tv_username.append(mPost?.title)
        tv_user_email.append(mPost?.id.toString())
        tv_user_id.append(mPost?.userId.toString())
        tv_post_details_body.text = mPost?.body
        getPostComments(mPost?.id.toString())
    }

    private fun getPostComments(postId: String) {
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            Constants.GET_POST_DETAILS + postId + Constants.GET_COMMENTS,
            null,
            Response.Listener<JSONArray> { response ->
                if (response.length() > 0)
                    displayComments(response)
            },
            Response.ErrorListener { error: VolleyError? ->
                Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
                comment_progress_bar.visibility = View.GONE
            }
        )
        mRequestQueue.add(jsonArrayRequest)
        comment_progress_bar.visibility = View.VISIBLE
    }

    private fun displayComments(response: JSONArray) {
        val comments: ArrayList<Comment> = ArrayList()
        for (i in 0 until response.length()) run {
            val jsonObject: JSONObject = ObjectExtractor.extractJsonObject(response, i)
            val postId: Int = ObjectExtractor.extractPostIdFromComment(jsonObject)
            val id: Int = ObjectExtractor.extractId(jsonObject)
            val name: String = ObjectExtractor.extractName(jsonObject)
            val email: String = ObjectExtractor.extractEmail(jsonObject)
            val body: String = ObjectExtractor.extractBody(jsonObject)
            val comment = Comment(postId, id, name, email, body)
            comments.add(comment)
        }
        all_comments_recycler_view.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = CommentListAdapter(comments)
        }
        comment_progress_bar.visibility = View.GONE
    }
}
