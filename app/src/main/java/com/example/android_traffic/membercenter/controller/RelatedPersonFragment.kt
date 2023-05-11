package com.example.android_traffic.membercenter.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentRelatedPersonBinding
import com.example.android_traffic.membercenter.viewmodel.RelatedPersonViewModel

class RelatedPersonFragment : Fragment() {
    private lateinit var binding: FragmentRelatedPersonBinding
    private lateinit var viewModel: RelatedPersonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: RelatedPersonViewModel by viewModels()
        binding = FragmentRelatedPersonBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



}