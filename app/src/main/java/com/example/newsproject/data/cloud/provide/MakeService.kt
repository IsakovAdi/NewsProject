package com.example.newsproject.data.cloud.provide

interface MakeService {
    fun <T> service(clasz: Class<T>): T
}