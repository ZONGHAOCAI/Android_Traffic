package com.example.android_traffic.membercenter.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_traffic.membercenter.viewmodel.MemberDataViewModel
import com.example.android_traffic.R

class MemberDataFragment : Fragment() {

    companion object {
        fun newInstance() = MemberDataFragment()
    }

    private lateinit var viewModel: MemberDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_member_data, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MemberDataViewModel::class.java)
        // TODO: Use the ViewModel
    }

}