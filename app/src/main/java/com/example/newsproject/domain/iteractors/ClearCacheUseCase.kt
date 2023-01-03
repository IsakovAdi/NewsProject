package com.example.newsprojectj200.domain

import android.content.Context
import com.example.newsprojectj200.data.cache.db.NewsDao
import com.google.gson.Gson
import java.util.*

interface ClearCacheUseCase {
    suspend operator fun invoke(context: Context)
}

class ClearCacheUseCaseImpl(private val dao: NewsDao) : ClearCacheUseCase {

    fun Date.startOfDay() =
        Date(this.year, this.month, this.date, this.hours, this.minutes, this.seconds)

    fun Date.addToHoursToCurrentDate() =
        Date(this.year, this.month, this.date, this.hours, this.minutes + 10, this.seconds)

    private companion object {
        const val LAST_CACHE_CLEAR_EDITOR_SAVE_KEY = "LAST_CACHE_CLEAR_EDITOR_SAVE_KEY"
        const val LAST_CACHE_CLEAR_SAVE_KEY = "LAST_CACHE_CLEAR_SAVE_KEY"
    }

    fun fetchLastClearCacheDate(context: Context): Date {
        val pref =
            context.getSharedPreferences(LAST_CACHE_CLEAR_EDITOR_SAVE_KEY, Context.MODE_PRIVATE)
        val lastUpdateDate = Gson().fromJson(pref.getString(LAST_CACHE_CLEAR_SAVE_KEY, null),
            Date::class.java) ?: Date().addToHoursToCurrentDate()
        return lastUpdateDate
    }

    fun saveCurrentUserFromCache(newDate: Date, context: Context) {
        context.getSharedPreferences(LAST_CACHE_CLEAR_EDITOR_SAVE_KEY, Context.MODE_PRIVATE)
            .edit()
            .putString(LAST_CACHE_CLEAR_SAVE_KEY, Gson().toJson(newDate))
            .commit()
    }

    override suspend fun invoke(context: Context) {
        val lastUpdateDate = fetchLastClearCacheDate(context).startOfDay().minutes
        if (lastUpdateDate <= Date().startOfDay().minutes) {
            dao.clearTable()
            saveCurrentUserFromCache(Date().addToHoursToCurrentDate(), context)
        }
    }
}

