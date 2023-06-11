package com.example.android_traffic.forum.controller

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_traffic.R
import com.example.android_traffic.core.model.ForumArticle
import com.example.android_traffic.core.service.Server.Companion.urlForumArticle
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.forum.viewmodel.ForumViewModel
import com.example.android_traffic.databinding.FragmentForumBinding
import com.google.gson.JsonObject

class ForumFragment : Fragment() {

    private lateinit var binding: FragmentForumBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding  = FragmentForumBinding.inflate(inflater, container, false)
        val viewModel : ForumViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            viewModel?.init()
            viewModel?.getNewTicket()
            rvForumArticleList.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.rvArticleList?.observe(viewLifecycleOwner){
                if (rvForumArticleList.adapter == null)
                    rvForumArticleList.adapter = ForumAdapter(it)
            }
            fabAddArticle.setOnClickListener {
                findNavController().navigate(R.id.action_forumFragment_to_addArticleFragment)
            }
            viewModel?.getNewTicket()
        }
    }
}