package com.shahu.novopaymicroblog

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.VolleyError
import com.shahu.novopaymicroblog.extractor.ObjectExtractor
import com.shahu.novopaymicroblog.fragments.HomeFragment
import com.shahu.novopaymicroblog.helper.Constants.GET_ALL_USERS_REQUEST
import com.shahu.novopaymicroblog.helper.IVolleyResponse
import com.shahu.novopaymicroblog.helper.VolleyRequest
import com.shahu.novopaymicroblog.models.User
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private var mIVolleyResponseCallback: IVolleyResponse? = null
    private var mVolleyRequest: VolleyRequest? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializationVolley()
        getAllUsers()
    }

    private fun getAllUsers() {
        mVolleyRequest!!.getAllUsers(GET_ALL_USERS_REQUEST)
    }

    private fun initializationVolley() {
        mIVolleyResponseCallback = object :
            IVolleyResponse {
            override fun onSuccessResponse(response: JSONArray, reqType: String) {
                if (response.length() > 0) {
                    when (reqType) {
                        GET_ALL_USERS_REQUEST -> displayAllUsers(response)
                    }
                }
            }

            override fun onRequestFailure(volleyError: VolleyError, reqType: String) {
                val toast =
                    Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG)
                toast.show()
            }

        }
        mVolleyRequest = VolleyRequest(
            this,
            mIVolleyResponseCallback
        )
    }

    private fun displayAllUsers(response: JSONArray) {
        val users: ArrayList<User> = ArrayList<User>()
        for (i in 0 until response.length()) run {
            val jsonObject: JSONObject = response.getJSONObject(i)
            val id: Int = ObjectExtractor.extractUserId(jsonObject)
            val name: String = ObjectExtractor.extractUserName(jsonObject)
            val email: String = ObjectExtractor.extractUserEmail(jsonObject)
            val user = User(id, name, email)
            users.add(user)
        }
        val homeFragment = HomeFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList("list", users)
        homeFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .add(R.id.main_fragment, homeFragment, "home_fragment").commit()
    }
}
