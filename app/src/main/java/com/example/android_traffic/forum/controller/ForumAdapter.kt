package com.example.android_traffic.forum.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.android_traffic.forum.model.ArticleContent
import com.example.android_traffic.R
import com.example.android_traffic.databinding.ItemForumArticleBinding
import com.example.android_traffic.forum.viewmodel.Forum2ViewModel

class ForumAdapter(private val articleContent : List<ArticleContent>):RecyclerView.Adapter<ForumAdapter.Holder>() {
    class Holder(val articleBinding : ItemForumArticleBinding) : RecyclerView.ViewHolder(articleBinding.root)

    override fun getItemCount(): Int {
        return articleContent.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val articleBinding = ItemForumArticleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        articleBinding.viewModel = Forum2ViewModel()
        articleBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return Holder(articleBinding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val articleContent = articleContent[position]
        holder.articleBinding.viewModel?.articleContent?.value = articleContent
        val bundle = Bundle()
        bundle.putSerializable("article",articleContent)

        holder.itemView.setOnClickListener {
            //判斷，如果ID與memberID相同 文章顯示可以編輯 FIXME
            Navigation.findNavController(it).navigate(R.id.action_forumFragment_to_forum2Fragment,bundle)
        }

    }
}