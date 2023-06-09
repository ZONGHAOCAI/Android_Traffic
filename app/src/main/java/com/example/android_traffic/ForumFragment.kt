package com.example.android_traffic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_traffic.databinding.FragmentForumBinding

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
            rvForumArticleList.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.rvArticleList?.observe(viewLifecycleOwner){
                if (rvForumArticleList.adapter == null)
                    rvForumArticleList.adapter = ForumAdapter(it)
            }
        }
    }



}