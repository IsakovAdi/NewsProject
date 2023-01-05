package com.example.newsproject.domain

import com.example.newsproject.domain.models.ArticleDomain
import kotlinx.coroutines.flow.Flow

interface ArticlesRepositoryFromCloud {

     fun getAllNews(
        keyword: String,
        sortBy: String,
        language: String,
    ): Flow<List<ArticleDomain>>

    fun getTopHeadlines(
        keyword: String,
        category: String
    ): Flow<List<ArticleDomain>>
//
//    fun getCategories(): List<ParamsDomain>
//    fun getSortBy(): List<ParamsDomain>
//    fun getAllLanguage(): List<ParamsDomain>
//    fun getAllCountries(): List<ParamsDomain>

}