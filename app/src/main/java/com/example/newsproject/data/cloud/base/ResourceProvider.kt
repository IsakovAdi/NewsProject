package com.example.newsprojectj200.data.cloud.base

import android.content.Context
import androidx.annotation.StringRes
import com.example.newsprojectj200.R
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface ResourceProvider {

    fun getString(@StringRes id: Int): String

    fun handleException(exception: Exception): String

    fun handleException(throwable: Throwable): String

    class Base(private val context: Context) : ResourceProvider {
        override fun getString(id: Int): String = context.getString(id)

        override fun handleException(exception: Exception): String {
            return when (exception) {
                is UnknownHostException -> getString(R.string.network_error)
                is SocketTimeoutException -> getString(R.string.network_error)
                is ConnectException -> getString(R.string.network_error)
                is HttpException -> getString(R.string.service_unavailable)
                else -> getString(R.string.generic_error)
            }
        }

        override fun handleException(throwable: Throwable): String {
            return when (throwable) {
                is UnknownHostException -> getString(R.string.network_error)
                is SocketTimeoutException -> getString(R.string.network_error)
                is ConnectException -> getString(R.string.network_error)
                is HttpException -> getString(R.string.service_unavailable)
                else -> getString(R.string.generic_error)
            }
        }

    }
}