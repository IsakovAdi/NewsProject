package com.example.newsprojectj200.domain.usecases

import com.example.newsapp.domain.Repository

class GetAllCategoriesUseCase(private val repository: Repository) {
    fun execute() = repository.getCategories()
}