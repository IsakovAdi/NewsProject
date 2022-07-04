package com.example.newsprojectj200.presentation.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.domain.models.Articles
import com.example.newsprojectj200.R
import com.example.newsprojectj200.databinding.NewsItemBinding
import com.squareup.picasso.Picasso

class NewsItemViewHolder(
    val binding: NewsItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(article:Articles) = binding.apply{
        author.text = article.author
        title.text = article.title
        desc.text = article.description
            source.text = article.source?.name
        if (article.urlToImage?.isEmpty() == true){
            newsImage.setImageResource(R.drawable.ic_all)
        }
        else{
            Picasso.get().load(article.urlToImage).into(newsImage)
        }
    }
}