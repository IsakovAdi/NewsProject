package com.example.newsproject.presentation.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.domain.models.ArticleDomain
import com.example.newsproject.data.cloud.base.ResourceProvider
import com.example.newsproject.domain.ArticlesRepositoryFromCache
import com.example.newsproject.domain.ArticlesRepositoryFromCloud
import com.example.newsproject.domain.Mapper
import com.example.newsproject.presentation.model.ArticleUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllNewsViewModel @Inject constructor(
    private val repository: ArticlesRepositoryFromCloud,
    private val listMapper: Mapper<List<ArticleDomain>, List<ArticleUi>>,
    private val mapper: Mapper<ArticleUi, ArticleDomain>,
    private val resourceProvider: ResourceProvider,
    private val storageRepository: ArticlesRepositoryFromCache,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<String>(replay = 0)
    val error get() = _errorFlow.asSharedFlow()

    private val sortByFlow = MutableStateFlow("relevancy")
    private val keywordFlow = MutableStateFlow("")
    private val languageFlow = MutableStateFlow("ru")

    private val sortByAndKeywordFlow = sortByFlow.combine(keywordFlow) { sortBy, keyword ->
        Pair(sortBy, keyword)
    }

    private val allSortParameters =
        sortByAndKeywordFlow.combine(languageFlow) { sortByAndKeyword, language ->
            SortModel(keyword = sortByAndKeyword.second,
                sortBy = sortByAndKeyword.first,
                language = language)
        }

    val allNewsSharedFlow = allSortParameters.flatMapLatest { sortTypes ->
        repository.getAllNews(
            keyword = sortTypes.keyword,
            sortBy = sortTypes.sortBy,
            language = sortTypes.language)
    }.map(listMapper::map)
        .flowOn(Dispatchers.Default)
        .catch { error: Throwable ->
            Log.d("NewsLog", error.toString())
            _errorFlow.emit(resourceProvider.handleException(error))
        }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


//    val allNewsStateFlow = allSortParameters.flatMapLatest { sortTypes ->
//        repository.getAllNews(sortTypes.keyword, sortTypes.sortBy, sortTypes.language)
//    }
//        .map(mapper::map)
//        .flowOn(Dispatchers.Default)
//        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun saveArticle(articleUi: ArticleUi) = viewModelScope.launch {
        storageRepository.saveArticle(mapper.map(articleUi))
        Log.d("SavedArticle", articleUi.url.toString())
    }

    fun updateSortBy(sortBy: String) = sortByFlow.tryEmit(sortBy)
    fun updateKeyword(keyword: String) = keywordFlow.tryEmit(keyword)

}

data class SortModel(val keyword: String, val sortBy: String, val language: String)