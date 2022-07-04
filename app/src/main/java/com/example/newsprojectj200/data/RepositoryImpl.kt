package com.example.newsprojectj200.data

import androidx.lifecycle.MutableLiveData
import com.example.newsapp.data.RetrofitInstance
import com.example.newsapp.domain.Repository
import com.example.newsapp.domain.models.News
import com.example.newsapp.domain.models.Params
import retrofit2.Response

object RepositoryImpl : Repository {

    val categoriesMLD = MutableLiveData(getCategories())

    fun changeEnableState(params: Params){
       categoriesMLD.value?.forEach {
           it.isClick = it.param == params.param
       }
    }
    override suspend fun getAllNews(
        keyword: String, sortBy: String,
        language: String, domains: String, apiKey: String
    ): Response<News> {
        return RetrofitInstance.api.getAllNews(
            keyword, sortBy, language, domains, apiKey
        )
    }

    override suspend fun getTopHeadlines(
        keyword: String, country: String,
        category: String, apiKey: String
    ): Response<News> {
        return RetrofitInstance.api.getTopHeadlines(keyword, country, category, apiKey)
    }

    override fun getCategories(): List<Params> {
        val categoriesList = mutableListOf<Params>()
        categoriesList.add(Params("Business", categories.BUSINESS, true))
        categoriesList.add(Params("Entertainment", categories.ENTERTAINMENT, false))
        categoriesList.add(Params("General", categories.GENERAL, false))
        categoriesList.add(Params("Health", categories.HEALTH, false))
        categoriesList.add(Params("Science", categories.SCIENCE, false))
        categoriesList.add(Params("Sports", categories.SPORTS, false))
        categoriesList.add(Params("Technology", categories.TECHNOLOGY, false))
        return categoriesList
    }

    override fun getSortBy(): List<Params> {
        val sortByList = mutableListOf<Params>()
        sortByList.add(Params("Published at", sortBy.PUBLISHEDAT, true))
        sortByList.add(Params("Popularity", sortBy.POPULARITY, false))
        sortByList.add(Params("Relevancy", sortBy.RELEVANCY, false))
        return sortByList
    }

    override fun getAllLanguage(): List<Params> {
        val languageList = mutableListOf<Params>()
        languageList.add(Params("Russia", ISO_639_1.RUSSIA, true))
        languageList.add(Params("Arabian", ISO_639_1.ARABIAN, false))
        languageList.add(Params("Germany", ISO_639_1.GERMANY, false))
        languageList.add(Params("English", ISO_639_1.ENGLISH, false))
        languageList.add(Params("Spain", ISO_639_1.SPAIN, false))
        languageList.add(Params("France", ISO_639_1.FRANCE, false))
        languageList.add(Params("Italy", ISO_639_1.ITALY, false))
        languageList.add(Params("Portuguese", ISO_639_1.PORTUGUESE, false))
        languageList.add(Params("Chinese", ISO_639_1.CHINESE, false))
        return languageList
    }

    override fun getAllCountries(): MutableList<Params> {
        val countriesList = mutableListOf<Params>()
        countriesList.add(Params("Russia", ISO_3166_1.RUSSIA, true))
        countriesList.add(Params("Argentina", ISO_3166_1.ARGENTINA, false))
        countriesList.add(Params("China", ISO_3166_1.CHINA, false))
        countriesList.add(Params("Cuba", ISO_3166_1.CUBA, false))
        countriesList.add(Params("Germany", ISO_3166_1.GERMANY, false))
        countriesList.add(Params("Egypt", ISO_3166_1.EGYPT, false))
        countriesList.add(Params("France", ISO_3166_1.FRANCE, false))
        countriesList.add(Params("Greece", ISO_3166_1.GREECE, false))
        countriesList.add(Params("England", ISO_3166_1.ENGLAND, false))
        countriesList.add(Params("America", ISO_3166_1.AMERICA, false))
        return countriesList
    }

    object ISO_639_1 {
        const val ARABIAN = "ar"
        const val GERMANY = "de"
        const val ENGLISH = "en"
        const val SPAIN = "es"
        const val FRANCE = "fr"
        const val ITALY = "it"
        const val PORTUGUESE = "pt"
        const val RUSSIA = "ru"
        const val CHINESE = "zh"
    }

    object ISO_3166_1 {
        const val ARGENTINA = "ar"
        const val CHINA = "cn"
        const val CUBA = "cu"
        const val GERMANY = "de"
        const val EGYPT = "eg"
        const val FRANCE = "fr"
        const val GREECE = "gr"
        const val RUSSIA = "ru"
        const val ENGLAND = "gb"
        const val AMERICA = "us"
    }

    object sortBy {
        const val RELEVANCY = "relevancy"
        const val POPULARITY = "popularity"
        const val PUBLISHEDAT = "publishedAt"
    }

    object categories {
        const val BUSINESS = "business"
        const val ENTERTAINMENT = "entertainment"
        const val GENERAL = "general"
        const val HEALTH = "health"
        const val SCIENCE = "science"
        const val SPORTS = "sports"
        const val TECHNOLOGY = "technology"
    }
}