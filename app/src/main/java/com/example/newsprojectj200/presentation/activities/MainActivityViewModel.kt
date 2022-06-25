package com.example.newsprojectj200.presentation.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.Api
import com.example.newsapp.domain.Repository
import com.example.newsapp.domain.models.Articles
import com.example.newsapp.domain.models.News
import com.example.newsapp.domain.usecases.GetAllNewsUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivityViewModel:ViewModel() {
    private val repository = Repository()

    private val getAllNewsUseCase = GetAllNewsUseCase(repository = repository)
    private val _error = MutableLiveData<String>()
    val error:LiveData<String>
    get() = _error
    private val _allNews = MutableLiveData<List<Articles>>()
    val allNews:LiveData<List<Articles>>
    get() = _allNews

    fun getAllNews(keyword: String,
                   language: Api.Languages,
                   apiKey: String) = viewModelScope.launch {
        val response = getAllNewsUseCase.execute(keyword,language.name, apiKey)
        if (response.isSuccessful){
            _allNews.value = response.body()?.articles
        }
        else{
            _error.value = response.message()
        }
    }

}