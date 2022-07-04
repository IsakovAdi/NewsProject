package com.example.newsprojectj200.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.newsapp.domain.models.Articles
import com.example.newsprojectj200.R
import com.example.newsprojectj200.databinding.NewsItemBinding
import com.example.newsprojectj200.presentation.callbacks.NewsItemDiffCallback
import com.example.newsprojectj200.presentation.viewholders.NewsItemViewHolder

class NewsAdapter : ListAdapter<Articles, NewsItemViewHolder>(NewsItemDiffCallback()) {

    var onNewsItemClickListener:((Articles) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder =
        NewsItemViewHolder(
            NewsItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
            )
        )
    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            onNewsItemClickListener?.invoke(getItem(position))
        }
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE
    }

    companion object {
        const val VIEW_TYPE = 1
        const val MAX_POOL_SIZE = 15
    }
}