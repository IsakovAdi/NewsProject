package com.example.newsprojectj200.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.domain.models.Params
import com.example.newsprojectj200.R
import com.example.newsprojectj200.presentation.callbacks.CategoriesItemDiffCallback
import com.example.newsprojectj200.presentation.viewholders.NewsCategoriesItemViewHolder
import okhttp3.internal.notify

class ParamsAdapter : RecyclerView.Adapter<NewsCategoriesItemViewHolder>() {
    var onParamClickListener: ((Params) -> Unit)? = null
    var paramsList = mutableListOf<Params>()
        set(value) {
            val callback = CategoriesItemDiffCallback(paramsList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsCategoriesItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.news_language_item_disabled
            VIEW_TYPE_ENABLED -> R.layout.news_language_item_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context)
            .inflate(layout, parent, false)
        return NewsCategoriesItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsCategoriesItemViewHolder, position: Int) {
        holder.bind(paramsList[position])
        holder.view.setOnClickListener {
            onParamClickListener?.invoke(paramsList[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = paramsList[position]
        return if (item.isClick) {
            VIEW_TYPE_ENABLED
        } else VIEW_TYPE_DISABLED
    }

    fun changeEnableState(params: Params) {
        var index = 0
        paramsList.forEach {
            if (it.param == params.param) {
                index = paramsList.indexOf(it)
            }
            it.isClick = it.param == params.param
        }
        notifyItemChanged(index)
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_POOL_SIZE_ENABLED = 5
        const val MAX_POOL_SIZE_DISABLED = 15
    }

    override fun getItemCount(): Int = paramsList.size


}