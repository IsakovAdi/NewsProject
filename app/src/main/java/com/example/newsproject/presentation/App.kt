package com.example.newsproject.presentation

import android.app.Application
import com.example.newsproject.domain.iteractors.ClearCacheUseCase
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