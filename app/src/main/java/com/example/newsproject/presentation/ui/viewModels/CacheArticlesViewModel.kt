package com.example.newsproject.presentation.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.data.cloud.base.ResourceProvider
import com.example.newsproject.domain.ArticlesRepositoryFromCache
import com.example.newsproject.domain.Mapper
import com.example.newsproject.domain.iteractors.DeleteArticleUseCase
import com.example.newsproject.domain.iteractors.DeleteArticleUseCaseImpl
import com.example.newsproject.domain.iteractors.GetAllSavedArticlesUseCase
import com.example.newsproject.domain.iteractors.GetAllSavedArticlesUseCaseImpl
import com.example.newsproject.domain.models.ArticleDomain
import com.example.newsproject.presentation.model.ArticleUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CacheArticlesViewModel @Inject constructor(
    private val repositoryFromCache: ArticlesRepositoryFromCache,
    private val listMapper: Mapper<List<ArticleDomain>, List<ArticleUi>>,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<String>(replay = 0)
    val error get() = _errorFlow.asSharedFlow()

    private val _successSavedSharedFlow = MutableSharedFlow<String>(replay = 0)
    val successSavedSharedFlow get() = _successSavedSharedFlow.asSharedFlow()

    val allNewsSharedFlow = repositoryFromCache.getAllSavedArticles()
        .map(listMapper::map)
        .flowOn(Dispatchers.Default)
        .catch { error: Throwable ->
            _errorFlow.emit(resourceProvider.handleException(error))
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun deleteSavedArticle(articleUrl: String) = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            repositoryFromCache.deleteSavedArticle(articleUrl = articleUrl)
        }
    }

}