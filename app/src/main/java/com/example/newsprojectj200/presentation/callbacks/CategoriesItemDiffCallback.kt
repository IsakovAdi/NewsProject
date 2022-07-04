package com.example.newsprojectj200.presentation.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.newsapp.domain.models.Params

class CategoriesItemDiffCallback(
    private val oldList: List<Params>,
    private val newList: List<Params>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].param == newList[newItemPosition].param

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

}