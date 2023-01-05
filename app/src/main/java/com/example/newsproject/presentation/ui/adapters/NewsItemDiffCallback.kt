package com.example.newsproject.presentation.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.newsproject.presentation.model.ArticleUi

class NewsItemDiffCallback(
    private val oldList: List<ArticleUi>,
    private val newList: List<ArticleUi>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].url == newList[newItemPosition].url

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}