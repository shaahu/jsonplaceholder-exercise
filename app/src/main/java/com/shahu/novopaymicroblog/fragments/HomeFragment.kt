package com.shahu.novopaymicroblog.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.shahu.novopaymicroblog.R
import com.shahu.novopaymicroblog.helper.Constants.FRAGMENT_CLICK_USER
import com.shahu.novopaymicroblog.helper.IFragmentListener
import com.shahu.novopaymicroblog.helper.ListAdapter
import com.shahu.novopaymicroblog.models.User
import kotlinx.android.synthetic.main.home_fragment.*
import java.util.*


/**
 * Created by Shahu Ronghe on 24, March, 2020
 * in Novopay Microblog
 */
private var usersList: ArrayList<Parcelable>? = null
private var mIFragmentListener: IFragmentListener? = null

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        usersList = arguments?.getParcelableArrayList<Parcelable>("list")
        return view
    }

    fun setFragmentCallback(callback: IFragmentListener) {
        mIFragmentListener = callback
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        all_users_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
        }
        all_users_recycler_view.adapter =
            ListAdapter(usersList) { user: User -> usernameClicked(user) }
    }

    private fun usernameClicked(user: User) {
        mIFragmentListener?.onFragmentItemClicked(user.id.toString(), FRAGMENT_CLICK_USER)
    }

}