package com.example.newsprojectj200.data.cloud.provide

interface MakeService {
    fun <T> service(clasz: Class<T>): T
}