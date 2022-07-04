package com.example.newsprojectj200.presentation.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.models.Articles
import com.example.newsapp.domain.models.Params
import com.example.newsprojectj200.data.RepositoryImpl
import com.example.newsprojectj200.domain.usecases.GetAllCategoriesUseCase
import com.example.newsprojectj200.domain.usecases.GetAllCountriesUseCase
import com.example.newsprojectj200.domain.usecases.GetTopNewsUseCase
import kotlinx.coroutines.launch

class TopNewsFragmentVM : ViewModel() {
    private val repository = RepositoryImpl

    private val getTopNewsUseCase = GetTopNewsUseCase(repository = repository)

    private val _allTopNews = MutableLiveData<List<Articles>>()
    val allTopNews: LiveData<List<Articles>> get() = _allTopNews

    private val _error = MutableLiveData<String>()

    fun getTopNews(keyword: String, country: String, category: String) {
        viewModelScope.launch {
            val response = getTopNewsUseCase.execute(keyword, country, category)
            if (response.isSuccessful) {
                _allTopNews.value = response.body()?.articles
            } else _error.value = response.message()
        }
    }

    fun getCategory():List<Params> = GetAllCategoriesUseCase(repository).execute()

    fun getCountry(): List<Params> = GetAllCountriesUseCase(repository).execute()

}