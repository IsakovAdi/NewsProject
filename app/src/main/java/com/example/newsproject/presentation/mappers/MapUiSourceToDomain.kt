package com.example.newsproject.presentation.mappers

import com.example.newsproject.domain.Mapper
import com.example.newsproject.domain.models.SourceDomain
import com.example.newsproject.presentation.model.SourceUi
import javax.inject.Inject

class MapUiSourceToDomain @Inject constructor() : Mapper<SourceUi, SourceDomain> {
    override fun map(from: SourceUi) = from.run {
        SourceDomain(sourceId = sourceId, name = name)
    }
}