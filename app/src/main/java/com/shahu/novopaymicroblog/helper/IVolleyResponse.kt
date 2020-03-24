package com.shahu.novopaymicroblog.helper

import com.android.volley.VolleyError
import org.json.JSONArray

/**
 * Created by Shahu Ronghe on 24, March, 2020
 * in Novopay Microblog
 */
interface IVolleyResponse {

    fun onSuccessResponse(response: JSONArray, reqType: String)

    fun onRequestFailure(volleyError: VolleyError, reqType: String)
}