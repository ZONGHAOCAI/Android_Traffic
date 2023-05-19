package com.example.android_traffic.membercenter.controller


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import com.example.android_traffic.databinding.FragmentRelatedPersonDataBinding
import com.example.android_traffic.membercenter.model.RelatedPerson
import com.example.android_traffic.membercenter.viewmodel.RelatedPersonDataViewModel

class RelatedPersonDataFragment : Fragment() {
    private lateinit var binding: FragmentRelatedPersonDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 呈現標題列
//        (requireActivity() as MainActivity).supportActionBar?.show()
        val viewModel: RelatedPersonDataViewModel by viewModels()
        binding = FragmentRelatedPersonDataBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {bundle ->
        bundle.getSerializable("relatedPerson")?.let {
            binding.viewModel?.relatedPerson?.value = it as RelatedPerson
        }

        }
    }



}