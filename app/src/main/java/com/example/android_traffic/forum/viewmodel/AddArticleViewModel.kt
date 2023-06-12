package com.example.android_traffic.forum.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.core.model.ForumArticle

class AddArticleViewModel : ViewModel() {
    val article : MutableLiveData<ForumArticle> by lazy { MutableLiveData(ForumArticle()) }
    val member: MutableLiveData<String> by lazy { MutableLiveData<String>() }

}