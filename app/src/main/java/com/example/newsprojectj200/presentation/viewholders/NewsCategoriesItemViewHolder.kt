package com.example.newsprojectj200.presentation.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.domain.models.Params
import com.example.newsprojectj200.R
import com.example.newsprojectj200.databinding.NewsItemBinding

class NewsCategoriesItemViewHolder(
    val view: View
) : RecyclerView.ViewHolder(view) {
    val lan = view.findViewById<TextView>(R.id.language_text)
    fun bind(l: Params) {
        lan.text = l.title
    }
}