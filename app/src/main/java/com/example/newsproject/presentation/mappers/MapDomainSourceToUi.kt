package com.example.newsprojectj200.presentation.mappers

import com.example.newsprojectj200.domain.Mapper
import com.example.newsprojectj200.domain.models.SourceDomain
import com.example.newsprojectj200.presentation.model.SourceUi
import javax.inject.Inject

class MapDomainSourceToUi @Inject constructor(): Mapper<SourceDomain, SourceUi> {
    override fun map(from: SourceDomain) = from.run {
        SourceUi(sourceId = sourceId, name = name)
    }
}