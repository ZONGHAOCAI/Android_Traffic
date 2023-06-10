package com.example.android_traffic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android_traffic.core.model.ForumArticle
import com.example.android_traffic.core.service.Server.Companion.urlForumArticle
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.databinding.FragmentAddArticleBinding
import com.google.gson.JsonObject

class AddArticleFragment : Fragment() {

    private lateinit var binding: FragmentAddArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddArticleBinding.inflate(inflater, container, false)
        val viewModel : AddArticleViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            btnCommit.setOnClickListener {
                if(etArticleTitle.text == null || etArticleTitle.text.isEmpty()){
                    etArticleTitle.error = "不可為空"
                    return@setOnClickListener
                }

                if(etArticleContent.text == null || etArticleContent.text.isEmpty()){
                    etArticleContent.error = "不可為空"
                    return@setOnClickListener
                }
                Log.d("MyTag","viewModel?.article?.value : ${viewModel?.article?.value}")
                val respBody = requestTask<JsonObject>(
                    urlForumArticle,"POST",viewModel?.article?.value
                )
                respBody?.run {
                    if(get("successful").asBoolean){
                        findNavController().popBackStack()
                        Toast.makeText(context, "新增成功", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }



}