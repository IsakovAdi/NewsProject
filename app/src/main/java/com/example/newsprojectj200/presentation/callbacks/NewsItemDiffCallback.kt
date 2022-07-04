package com.example.newsprojectj200.presentation.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.newsapp.domain.models.Articles

class NewsItemDiffCallback:DiffUtil.ItemCallback<Articles>() {
    override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
        return oldItem.url == newItem.url
    }
    override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
        return oldItem == newItem
    }
}