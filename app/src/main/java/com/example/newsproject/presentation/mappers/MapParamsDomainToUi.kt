package com.example.newsproject.presentation.mappers

import com.example.newsproject.domain.models.ParamsDomain
import com.example.newsproject.domain.Mapper
import com.example.newsproject.presentation.model.ParamsUi

class MapParamsDomainToUi : Mapper<ParamsDomain, ParamsUi> {
    override fun map(from: ParamsDomain) = from.run {
        ParamsUi(title = title, param = param)
    }
}