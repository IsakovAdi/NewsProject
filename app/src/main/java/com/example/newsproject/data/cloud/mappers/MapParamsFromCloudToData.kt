package com.example.newsprojectj200.data.cloud.mappers

import com.example.newsapp.domain.models.ParamsDomain
import com.example.newsprojectj200.data.cloud.models.ParamsCloud
import com.example.newsprojectj200.data.models.ParamsData
import com.example.newsprojectj200.domain.Mapper

class MapParamsFromCloudToData : Mapper<ParamsCloud, ParamsData> {
    override fun map(from: ParamsCloud)= from.run {
        ParamsData(title = title, param = param)
    }
}