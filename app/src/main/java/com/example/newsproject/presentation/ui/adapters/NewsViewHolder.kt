package com.example.newsproject.presentation.ui.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsproject.R
import com.example.newsproject.databinding.NewsItemBinding
import com.example.newsproject.presentation.model.ArticleUi
import com.squareup.picasso.Picasso

class NewsViewHolder(
    private val view: View,
) : RecyclerView.ViewHolder(view) {

    private val newsTime: TextView = view.findViewById(R.id.news_time_text)
    private val newsTitle: TextView = view.findViewById(R.id.news_title_text)
    private val newsImage = view.findViewById<ImageView>(R.id.news_image)

    fun bind(article: ArticleUi) {

        newsTime.text = article.publishedAt
        newsTitle.text = article.title
        if (article.urlToImage != null) {
            Picasso.get().load(article.urlToImage).into(newsImage)
        }
    }

}