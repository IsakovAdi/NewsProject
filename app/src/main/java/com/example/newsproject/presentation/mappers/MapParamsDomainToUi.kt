package com.example.newsprojectj200.presentation.mappers

import com.example.newsapp.domain.models.ParamsDomain
import com.example.newsprojectj200.domain.Mapper
import com.example.newsprojectj200.presentation.model.ParamsUi

class MapParamsDomainToUi : Mapper<ParamsDomain, ParamsUi> {
    override fun map(from: ParamsDomain) = from.run {
        ParamsUi(title = title, param = param)
    }
}