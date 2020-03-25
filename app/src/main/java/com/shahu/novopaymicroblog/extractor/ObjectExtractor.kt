package com.shahu.novopaymicroblog.extractor

import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Shahu Ronghe on 24, March, 2020
 * in Novopay Microblog
 */
const val ID: String = "id"
const val USER_ID: String = "userId"
const val POST_ID: String = "postId"
const val NAME: String = "name"
const val EMAIL: String = "email"
const val TITLE: String = "title"
const val BODY: String = "body"

object ObjectExtractor {

    fun extractJsonObject(response: JSONArray, i: Int): JSONObject {
        return response.optJSONObject(i)
    }

    fun extractId(jsonObject: JSONObject): Int {
        return jsonObject.optInt(ID)
    }

    fun extractName(jsonObject: JSONObject): String {
        return jsonObject.optString(NAME)
    }

    fun extractEmail(jsonObject: JSONObject): String {
        return jsonObject.optString(EMAIL)
    }

    fun extractTitle(jsonObject: JSONObject): String {
        return jsonObject.optString(TITLE)
    }

    fun extractBody(jsonObject: JSONObject): String {
        return jsonObject.optString(BODY)
    }

    fun extractUserIdFromPost(jsonObject: JSONObject): Int {
        return jsonObject.optInt(USER_ID)
    }

    fun extractPostIdFromComment(jsonObject: JSONObject): Int {
        return jsonObject.optInt(POST_ID)
    }
}