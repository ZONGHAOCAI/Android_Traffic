package com.example.android_traffic.forum.controller

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android_traffic.MainActivityViewModel
import com.example.android_traffic.core.model.ForumArticle
import com.example.android_traffic.forum.viewmodel.AddArticleViewModel
import com.example.android_traffic.core.service.Server.Companion.urlForumArticle
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.databinding.FragmentAddArticleBinding
import com.example.android_traffic.forum.viewmodel.ForumViewModel
import com.example.android_traffic.ticket.model.Token
import com.google.gson.JsonObject

class AddArticleFragment : Fragment() {

    private lateinit var binding: FragmentAddArticleBinding
    private val myTag = "TAG_${javaClass.simpleName}"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddArticleBinding.inflate(inflater, container, false)
        val viewModel: AddArticleViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            btnCommit.setOnClickListener {
                if (etArticleTitle.text == null || etArticleTitle.text.isEmpty()) {
                    etArticleTitle.error = "不可為空"
                    return@setOnClickListener
                }
                if (etArticleContent.text == null || etArticleContent.text.isEmpty()) {
                    etArticleContent.error = "不可為空"
                    return@setOnClickListener
                }

//                Toast.makeText(context, "${viewModel?.article?.value}", Toast.LENGTH_SHORT).show()
                loadPreferences()//去偏好設定拿memID
                Log.d(myTag,"memID : ${viewModel!!.member.value!!.toInt()}")
                Log.d(myTag,"title : ${viewModel!!.article.value!!.title}")
                Log.d(myTag,"content : ${viewModel!!.article.value!!.content}")

                val addArticle = ForumArticle()
                addArticle.memberID = viewModel!!.member.value!!.toInt()
                println("viewModel!!.article.value!!.title : ${viewModel?.article?.value?.title}")
                addArticle.title = viewModel!!.article.value!!.title
                addArticle.content = viewModel!!.article.value!!.content
                Log.d("MyTag", "viewModel?.article?.value : ${viewModel?.article?.value}")
                val respBody = requestTask<JsonObject>(
                    urlForumArticle, "POST", addArticle
                )
                respBody?.run {
                    if (get("successful").asBoolean) {
                        findNavController().popBackStack()
                        Toast.makeText(context, "新增成功", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun loadPreferences() {
        with(binding) {
            val preferences = Token().getEncryptedPreferences(requireContext())
            viewModel?.member?.value = preferences.getString("MemId", "")
            val myTag = "TAG_${javaClass.simpleName}"
            Log.d(
                myTag,
                "getString: ${preferences.getString("MemId", "")?.javaClass?.simpleName}"
            )
        }
    }
}