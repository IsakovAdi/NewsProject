package com.example.newsproject.data.cloud.source

import com.example.newsproject.data.cloud.base.ResourceProvider
import com.example.newsproject.domain.Mapper
import com.example.newsproject.domain.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseCloudDataSource(private val resourceProvider: ResourceProvider) {

    suspend fun <T, K> safeApiCall(
        mapper: Mapper<T, K>,
        apiCall: suspend () -> Response<T>,
    ): Resource<K> {
        try {
            val response = withContext(Dispatchers.IO) { apiCall() }
            if (response.isSuccessful) {
                val body = withContext(Dispatchers.Default) { response.body() }
                body?.let { return Resource.success(data = mapper.map(body)) }
            }
            return Resource.error(message = response.message())
        } catch (e: Exception) {
            return Resource.error(resourceProvider.handleException(e))
        }
    }
}