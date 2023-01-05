package com.example.newsproject.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsproject.R
import com.example.newsproject.databinding.NewsItemBinding
import com.example.newsproject.presentation.model.ArticleUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsAdapter(
    private val listener: RecyclerOnClickListener,
) : RecyclerView.Adapter<NewsViewHolder>() {
    var articlesList = listOf<ArticleUi>()
        set(value) {
            val callback = NewsItemDiffCallback(articlesList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false))

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articlesList[position])
        holder.itemView.setOnClickListener {
            listener.onClick(articlesList[position])
        }

        holder.itemView.setOnLongClickListener {
            listener.onSaveClick(articlesList[position])
            true
        }
    }

    override fun getItemCount() =
        articlesList.size

    interface RecyclerOnClickListener {
        fun onClick(articleUi: ArticleUi)
        fun onSaveClick(articleUi: ArticleUi)
    }
}