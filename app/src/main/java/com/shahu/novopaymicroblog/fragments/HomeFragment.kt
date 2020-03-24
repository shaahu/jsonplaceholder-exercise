package com.shahu.novopaymicroblog.fragments

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.shahu.novopaymicroblog.R
import com.shahu.novopaymicroblog.helper.ListAdapter
import kotlinx.android.synthetic.main.home_fragment.*
import java.util.*


/**
 * Created by Shahu Ronghe on 24, March, 2020
 * in Novopay Microblog
 */
var usersList: ArrayList<Parcelable>? = null

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.home_fragment, container, false)

        usersList = arguments?.getParcelableArrayList<Parcelable>("list")

        Log.d("titanic", usersList.toString())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        all_users_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(usersList)
        }
    }
}