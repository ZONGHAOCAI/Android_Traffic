package com.example.android_traffic.forum.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.android_traffic.forum.model.ArticleContent
import com.example.android_traffic.databinding.FragmentForum2Binding
import com.example.android_traffic.forum.viewmodel.Forum2ViewModel

class Forum2Fragment : Fragment() {

    private lateinit var binding: FragmentForum2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForum2Binding.inflate(inflater, container, false)
        val viewModel : Forum2ViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            arguments?.let {
                it.getSerializable("article")?.let {
                    viewModel?.articleContent?.value = it as ArticleContent
                }
            }
        }
    }



}