package com.example.newsapp.presentattion.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.Api
import com.example.newsapp.domain.models.Language

class LanguageAdapter(
    private val listener:LanguageOnClickListener
): RecyclerView.Adapter<LanguageAdapter.Holder>() {
     var languagesList:List<Language> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_language_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(languagesList[position])
    }

    override fun getItemCount(): Int {
       return languagesList.size
    }
    inner class Holder(view:View):RecyclerView.ViewHolder(view) {
        private val lan = view.findViewById<TextView>(R.id.language_text)
        fun bind(l: Language){
            lan.text = l.title
            itemView.setOnClickListener {
                listener.onClick(l.language)
            }
        }
    }

    interface LanguageOnClickListener {
        fun onClick(l:Api.Languages)
    }
}