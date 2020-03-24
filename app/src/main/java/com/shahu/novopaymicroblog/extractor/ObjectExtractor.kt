package com.shahu.novopaymicroblog.extractor

import org.json.JSONObject

/**
 * Created by Shahu Ronghe on 24, March, 2020
 * in Novopay Microblog
 */
const val USER_ID: String = "id"
const val USER_NAME: String = "name"
const val USER_EMAIL: String = "email"

object ObjectExtractor {
    fun extractUserId(jsonObject: JSONObject): Int {
        return jsonObject.optInt(USER_ID)
    }

    fun extractUserName(jsonObject: JSONObject): String {
        return jsonObject.optString(USER_NAME)
    }

    fun extractUserEmail(jsonObject: JSONObject): String {
        return jsonObject.optString(USER_EMAIL)
    }
}