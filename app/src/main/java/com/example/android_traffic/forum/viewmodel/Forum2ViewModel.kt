package com.example.android_traffic.forum.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.forum.model.ArticleContent

class Forum2ViewModel : ViewModel() {
    val articleContent : MutableLiveData<ArticleContent> by lazy { MutableLiveData() }
}