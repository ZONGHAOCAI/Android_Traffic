package com.example.android_traffic.forum.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.core.model.ForumArticle
import com.example.android_traffic.forum.model.ArticleContent

class Forum2ViewModel : ViewModel() {
    val articleContent : MutableLiveData<ForumArticle> by lazy { MutableLiveData() }
}