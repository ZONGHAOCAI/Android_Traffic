package com.example.android_traffic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Forum2ViewModel : ViewModel() {
    val articleContent : MutableLiveData<ArticleContent> by lazy { MutableLiveData() }
}