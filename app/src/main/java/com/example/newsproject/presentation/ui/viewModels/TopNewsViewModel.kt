package com.example.newsproject.presentation.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.data.cloud.base.ResourceProvider
import com.example.newsproject.domain.ArticlesRepositoryFromCloud
import com.example.newsproject.domain.Mapper
import com.example.newsproject.domain.models.ArticleDomain
import com.example.newsproject.presentation.model.ArticleUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TopNewsViewModel @Inject constructor(
    private val repository: ArticlesRepositoryFromCloud,
    private val mapper: Mapper<List<ArticleDomain>, List<ArticleUi>>,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {
    private val _errorFlow = MutableSharedFlow<String>(replay = 0)
    val error get() = _errorFlow.asSharedFlow()

    private val sortByFlow = MutableStateFlow("relevancy")
    private val keywordFlow = MutableStateFlow("")

    private val sortByAndKeywordFlow = sortByFlow.combine(keywordFlow) { sortBy, keyword ->
        Pair(sortBy, keyword)
    }

    val topNewsStateFlow = sortByAndKeywordFlow.flatMapLatest { sortTypes ->
        repository.getTopHeadlines(
            keyword = sortTypes.second,
            category = sortTypes.first)
    }
        .map(mapper::map)
        .flowOn(Dispatchers.Default)
        .catch { error:Throwable->
            _errorFlow.emit(resourceProvider.handleException(error))
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun updateCategory(sortBy: String) = sortByFlow.tryEmit(sortBy)
    fun updateKeyword(keyword: String) = keywordFlow.tryEmit(keyword)

}