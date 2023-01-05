package com.example.newsproject.domain

interface Mapper<From, To> {
    fun map(from: From): To
}