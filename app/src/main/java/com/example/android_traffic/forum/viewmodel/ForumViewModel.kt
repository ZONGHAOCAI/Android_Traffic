package com.example.android_traffic.forum.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_traffic.core.model.ForumArticle
import com.example.android_traffic.core.model.Ticket
import com.example.android_traffic.core.service.Server
import com.example.android_traffic.core.service.Server.Companion.urlForumArticle
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.forum.model.ArticleContent
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ForumViewModel : ViewModel() {
    val myTag = "TAG_${javaClass.simpleName}"
    var articleList = mutableListOf<ForumArticle>()
    //live data wrapper
    val rvArticleList : MutableLiveData<List<ForumArticle>> by lazy { MutableLiveData<List<ForumArticle>>() }
    val forumArticle : MutableLiveData<ForumArticle> by lazy { MutableLiveData() }


    fun init() {
        val type = object : TypeToken<List<ForumArticle>>() {}.type

        rvArticleList.value =
            requestTask<List<ForumArticle>>(urlForumArticle,"GET", respBodyType = type)
        articleList = rvArticleList.value!!.toMutableList()

    }

    fun refreshArticle() {//reFresh
        viewModelScope.launch {
            while (isActive) {
                val type = object : TypeToken<List<ForumArticle>>() {}.type
                val article =
                    requestTask<List<ForumArticle>>(urlForumArticle,"GET", respBodyType = type)
                val oldTicket = mutableListOf<ForumArticle>()
                if (article != null) {
                    for (i in article) {
                        oldTicket.add(i)
                    }
                }
//                Log.d(myTag, "oldMessageList: ${oldTicket} ")
                rvArticleList.value = oldTicket
//                Log.d(myTag, "rvArticleList: ${rvArticleList.value} ")
                delay(30000)
            }
        }
    }


}