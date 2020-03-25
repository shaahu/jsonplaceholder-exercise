package com.shahu.novopaymicroblog

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.shahu.novopaymicroblog.extractor.ObjectExtractor
import com.shahu.novopaymicroblog.helper.Constants
import com.shahu.novopaymicroblog.adapters.UserListAdapter
import com.shahu.novopaymicroblog.models.User
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private lateinit var mRequestQueue: RequestQueue
    private val TAG = this.javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRequestQueue = Volley.newRequestQueue(this)
        getAllUsers()
    }

    private fun getAllUsers() {
        val jsonObjectRequest = JsonArrayRequest(Request.Method.GET,
            Constants.GET_USERS,
            null,
            Response.Listener<JSONArray> { response ->
                if (response.length() > 0)
                    displayAllUsers(response)
            }, Response.ErrorListener { error: VolleyError? ->
                Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
            })
        mRequestQueue.add(jsonObjectRequest)
    }

    private fun displayAllUsers(response: JSONArray) {
        val users: ArrayList<User> = ArrayList()
        for (i in 0 until response.length()) run {
            val jsonObject: JSONObject = ObjectExtractor.extractJsonObject(response, i)
            val id: Int = ObjectExtractor.extractId(jsonObject)
            val name: String = ObjectExtractor.extractName(jsonObject)
            val email: String = ObjectExtractor.extractEmail(jsonObject)
            val user = User(id, name, email)
            users.add(user)
        }
        all_users_recycler_view.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter =
                UserListAdapter(users) { user: User ->
                    usernameClicked(user)
                }
        }
    }

    private fun usernameClicked(user: User) {
        val intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }
}
