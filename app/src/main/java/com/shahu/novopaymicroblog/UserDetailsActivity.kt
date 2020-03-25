package com.shahu.novopaymicroblog

import android.content.Intent
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
import com.shahu.novopaymicroblog.adapters.PostListAdapter
import com.shahu.novopaymicroblog.extractor.ObjectExtractor
import com.shahu.novopaymicroblog.helper.Constants
import com.shahu.novopaymicroblog.models.Post
import com.shahu.novopaymicroblog.models.User
import kotlinx.android.synthetic.main.activity_user_details.*
import org.json.JSONArray
import org.json.JSONObject

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var mRequestQueue: RequestQueue
    private val TAG = this.javaClass.simpleName
    private var mUser: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        mRequestQueue = Volley.newRequestQueue(this)
        mUser = intent.getParcelableExtra("user")
        if (mUser != null) {
            inflateView()
        }
    }

    private fun inflateView() {
        tv_username.append(mUser?.name)
        tv_user_email.append(mUser?.email)
        tv_user_id.append(mUser?.id.toString())
        getUserPosts(mUser?.id.toString())
    }

    private fun getUserPosts(id: String) {
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            Constants.GET_POSTS_BY_USER + id,
            null,
            Response.Listener<JSONArray> { response ->
                if (response.length() > 0)
                    displayUserDetails(response)
            },
            Response.ErrorListener { error: VolleyError? ->
                Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
                post_progress_bar.visibility = View.GONE
            }
        )
        mRequestQueue.add(jsonArrayRequest)
        post_progress_bar.visibility = View.VISIBLE
    }

    private fun displayUserDetails(response: JSONArray) {
        val posts: ArrayList<Post> = ArrayList()
        for (i in 0 until response.length()) run {
            val jsonObject: JSONObject = ObjectExtractor.extractJsonObject(response, i)
            val userId: Int = ObjectExtractor.extractUserIdFromPost(jsonObject)
            val id: Int = ObjectExtractor.extractId(jsonObject)
            val title: String = ObjectExtractor.extractTitle(jsonObject)
            val body: String = ObjectExtractor.extractBody(jsonObject)
            val post = Post(userId, id, title, body)
            posts.add(post)
        }
        all_post_recycler_view.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = PostListAdapter(posts) { post: Post ->
                postClicked(post)
            }
        }
        post_progress_bar.visibility = View.GONE
    }

    private fun postClicked(post: Post) {
        val intent = Intent(this, PostDetailsActivity::class.java)
        intent.putExtra("post", post)
        startActivity(intent)
    }
}
