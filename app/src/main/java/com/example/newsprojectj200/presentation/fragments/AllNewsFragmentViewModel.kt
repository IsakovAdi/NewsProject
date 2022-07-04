package com.example.newsprojectj200.presentation.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.models.Articles
import com.example.newsapp.domain.models.Params
import com.example.newsapp.domain.usecases.GetAllNewsUseCase
import com.example.newsprojectj200.data.RepositoryImpl
import com.example.newsprojectj200.domain.usecases.GetAllLanguageUseCase
import com.example.newsprojectj200.domain.usecases.GetSortByParamsUseCase
import kotlinx.coroutines.launch

class AllNewsFragmentViewModel : ViewModel() {
    private val repository = RepositoryImpl

    private val getAllNewsUseCase = GetAllNewsUseCase(repository)

    private val _error = MutableLiveData<String>()
    private var _isFirst: MutableLiveData<Boolean> = MutableLiveData(true)

    private val _allNews = MutableLiveData<List<Articles>>()
    val allNews: LiveData<List<Articles>> get() = _allNews

    fun getAllNews(keyword: String, sortBy: String, lan: String) {
        viewModelScope.launch {
            val response = getAllNewsUseCase.execute(keyword, sortBy, lan)
            if (response.isSuccessful) {
                _allNews.value = response.body()?.articles
            } else _error.value = response.message()
        }
    }

    fun getSortByParam(): List<Params> = GetSortByParamsUseCase(repository).execute()

    fun getLanguage(): List<Params> = GetAllLanguageUseCase(repository).execute()
}