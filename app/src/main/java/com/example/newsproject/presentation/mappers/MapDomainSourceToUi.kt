package com.example.newsproject.presentation.mappers

import com.example.newsproject.domain.Mapper
import com.example.newsproject.domain.models.SourceDomain
import com.example.newsproject.presentation.model.SourceUi
import javax.inject.Inject

class MapDomainSourceToUi @Inject constructor(): Mapper<SourceDomain, SourceUi> {
    override fun map(from: SourceDomain) = from.run {
        SourceUi(sourceId = sourceId, name = name)
    }
}