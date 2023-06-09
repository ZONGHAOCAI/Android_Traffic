package com.example.android_traffic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ForumViewModel : ViewModel() {
    var articleList = mutableListOf<ArticleContent>()
    //live data wrapper
    val rvArticleList : MutableLiveData<List<ArticleContent>> by lazy { MutableLiveData<List<ArticleContent>>() }

    init {
        loadArticle()
    }

    fun loadArticle(){
        val list = mutableListOf<ArticleContent>()
        list.add(ArticleContent("天氣真好","123"))
        list.add(ArticleContent("做不完根本做不完","好累喔"))
        list.add(ArticleContent("眼睛閉閉，快樂元氣","我是廢文"))
        list.add(ArticleContent("RecyclerView","456"))
        list.add(ArticleContent("Layout提升術","趕進度"))
        list.add(ArticleContent("10天速成Kotlin","我是廢文RR"))
        list.add(ArticleContent("報名下一班","789"))
        list.add(ArticleContent("雙眼開開，準備投胎","好累喔"))
        list.add(ArticleContent("眼睛閉閉，快樂元氣","我是廢文"))
        list.add(ArticleContent("天氣真好","012"))
        list.add(ArticleContent("做不完根本做不完","趕進度"))
        list.add(ArticleContent("眼睛閉閉，快樂元氣","我是廢文RR"))
        this.articleList = list
        this.rvArticleList.value = this.articleList
    }
}