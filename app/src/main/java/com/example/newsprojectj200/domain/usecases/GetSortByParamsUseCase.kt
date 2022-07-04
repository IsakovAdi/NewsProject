package com.example.newsprojectj200.domain.usecases

import com.example.newsapp.domain.Repository

class GetSortByParamsUseCase(private val repository: Repository) {
    fun execute() = repository.getSortBy()
}