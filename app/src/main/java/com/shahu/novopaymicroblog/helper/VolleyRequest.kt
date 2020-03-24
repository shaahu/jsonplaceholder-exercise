package com.shahu.novopaymicroblog.helper

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Shahu Ronghe on 24, March, 2020
 * in Novopay Microblog
 */
class VolleyRequest(context: Context?, IVolleyResponse: IVolleyResponse?) {
    private val TAG = this.javaClass.simpleName
    private val mRequestQueue: RequestQueue = Volley.newRequestQueue(context)
    private val mIVolleyResponse: IVolleyResponse? = IVolleyResponse


    fun getAllUsers(requestType: String) {
        val jsonObjectRequest = JsonArrayRequest(Request.Method.GET,
            Constants.USERS, null,
            Response.Listener<JSONArray> { response ->
                mIVolleyResponse?.onSuccessResponse(response, requestType)
            }, Response.ErrorListener { error: VolleyError? ->
                Log.e(TAG, error.toString())
            })
        mRequestQueue.add(jsonObjectRequest)
    }
}