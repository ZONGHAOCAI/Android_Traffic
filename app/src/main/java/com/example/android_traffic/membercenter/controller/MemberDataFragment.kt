package com.example.android_traffic.membercenter.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.android_traffic.membercenter.viewmodel.MemberDataViewModel
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentMemberDataBinding
import com.example.android_traffic.databinding.FragmentRelatedPersonDataBinding
import com.example.android_traffic.membercenter.viewmodel.RelatedPersonDataViewModel

class MemberDataFragment : Fragment() {
    private lateinit var binding: FragmentMemberDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: MemberDataViewModel by viewModels()
        binding = FragmentMemberDataBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){

        }
    }


}