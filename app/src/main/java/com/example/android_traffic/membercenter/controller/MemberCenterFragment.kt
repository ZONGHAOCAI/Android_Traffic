package com.example.android_traffic.membercenter.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_traffic.membercenter.viewmodel.MemberCenterViewModel
import com.example.android_traffic.R

class MemberCenterFragment : Fragment() {

    companion object {
        fun newInstance() = MemberCenterFragment()
    }

    private lateinit var viewModel: MemberCenterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_member_center, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MemberCenterViewModel::class.java)
        // TODO: Use the ViewModel
    }

}