package com.example.android_traffic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.core.model.ForumArticle

class AddArticleViewModel : ViewModel() {
    val article : MutableLiveData<ForumArticle> by lazy { MutableLiveData() }

}