package com.example.newsprojectj200.presentation

import android.app.Application
import com.example.newsprojectj200.data.cache.db.NewsDao
import com.example.newsprojectj200.domain.ClearCacheUseCase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var clearCacheUseCase: ClearCacheUseCase


    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    override fun onCreate() {
        super.onCreate()
        scope.launch {
            clearCacheUseCase(this@App)
        }
    }
}