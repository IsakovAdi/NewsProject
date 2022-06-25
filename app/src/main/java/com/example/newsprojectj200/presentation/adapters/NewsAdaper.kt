package com.example.newsapp.presentattion.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.domain.models.Articles
import com.example.newsprojectj200.databinding.NewsItemBinding

class NewsAdaper : RecyclerView.Adapter<NewsAdaper.Holder>() {
    var languagesList: List<Articles> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class Holder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Articles) = binding.apply {
            newsImage

            author.text = article.author
            title.text = article.title
            desc.text = article.description
            source.text = article.source.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdaper.Holder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: NewsAdaper.Holder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}