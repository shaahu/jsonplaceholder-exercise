package com.shahu.novopaymicroblog.helper

/**
 * Created by Shahu Ronghe on 24, March, 2020
 * in Novopay Microblog
 */

object Constants {
    //URL
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    const val GET_USERS = BASE_URL + "users"

    const val GET_POSTS_BY_USER = BASE_URL + "posts?userId="

    const val GET_POST_DETAILS = BASE_URL + "posts/"

    const val GET_COMMENTS = "/comments"

    //Request Types
    const val REQUEST_TYPE_GET_ALL_USERS = "get_all_users"

    const val REQUEST_TYPE_GET_USERS_POSTS = "get_users_post"

    //Fragment Click Types
    const val FRAGMENT_CLICK_USER = "user_click"
}