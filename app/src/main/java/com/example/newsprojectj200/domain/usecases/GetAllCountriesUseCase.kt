package com.example.newsprojectj200.domain.usecases

import com.example.newsapp.domain.Repository

class GetAllCountriesUseCase(private val repository: Repository) {
    fun execute() = repository.getAllCountries()
}