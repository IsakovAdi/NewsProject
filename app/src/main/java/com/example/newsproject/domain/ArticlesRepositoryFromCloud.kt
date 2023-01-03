package com.example.newsprojectj200.domain

import com.example.newsapp.domain.models.ArticleDomain
import com.example.newsapp.domain.models.NewsDomain
import com.example.newsapp.domain.models.ParamsDomain
import kotlinx.coroutines.flow.Flow

interface ArticlesRepositoryFromCloud {

     fun getAllNews(
        keyword: String,
        sortBy: String,
        language: String,
    ): Flow<List<ArticleDomain>>

    fun getTopHeadlines(
        keyword: String,
        country: String,
        category: String
    ): Flow<List<ArticleDomain>>
//
//    fun getCategories(): List<ParamsDomain>
//    fun getSortBy(): List<ParamsDomain>
//    fun getAllLanguage(): List<ParamsDomain>
//    fun getAllCountries(): List<ParamsDomain>

}