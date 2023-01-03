package com.example.newsprojectj200.data.cache.db

import androidx.room.TypeConverter
import com.example.newsprojectj200.data.cache.models.SourceStorage
import org.json.JSONObject

class SourceConverter {
    private companion object {
        const val SOURCE_NAME_KEY = "SOURCE_NAME_KEY"
        const val SOURCE_ID_KEY = "SOURCE_ID_KEY"
    }

    @TypeConverter
    fun toSourceStorage(source: String): SourceStorage {
        try {
            val json = JSONObject(source)
            return SourceStorage(
                name = json.getString(SOURCE_NAME_KEY),
                sourceId = json.getString(SOURCE_ID_KEY),
            )
        }catch (e:Exception){
            return SourceStorage(String(), String())
        }
    }

    @TypeConverter
    fun fromSourceStorage(sourceStorage: SourceStorage): String {
        return JSONObject().apply {
            put(SOURCE_NAME_KEY, sourceStorage.name)
            put(SOURCE_ID_KEY, sourceStorage.sourceId)
        }.toString()
    }
}