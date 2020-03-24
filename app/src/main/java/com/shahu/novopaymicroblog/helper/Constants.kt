package com.shahu.novopaymicroblog.helper

/**
 * Created by Shahu Ronghe on 24, March, 2020
 * in Novopay Microblog
 */

object Constants {
    //URL
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    const val USERS = BASE_URL + "users"

    const val POSTS_BY_USER = BASE_URL + "posts?userId="

    const val POST_DETAILS = BASE_URL + "posts/"

    const val COMMENTS = "/comments"

    //Request Types
    const val GET_ALL_USERS_REQUEST = "get_all_users"

    //Fragment Click Types
    const val FRAGMENT_CLICK_USER = "user_click"
}