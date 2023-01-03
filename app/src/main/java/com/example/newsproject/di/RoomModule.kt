package com.example.newsprojectj200.di

import android.content.Context
import androidx.room.Room
import com.example.newsprojectj200.data.cache.db.AllNewsDatabase
import com.example.newsprojectj200.data.cache.db.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    fun provideAllNewsDatabase(@ApplicationContext context: Context): AllNewsDatabase {
        return Room
            .databaseBuilder(context, AllNewsDatabase::class.java, AllNewsDatabase.DATABASE_NAME)
            .build()
    }

    @Provides
    fun provideAllNewsDao(database: AllNewsDatabase): NewsDao = database.newsDao()


}