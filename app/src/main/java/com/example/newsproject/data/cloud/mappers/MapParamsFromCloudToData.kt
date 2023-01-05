package com.example.newsproject.data.cloud.mappers

import com.example.newsproject.data.cloud.models.ParamsCloud
import com.example.newsproject.data.models.ParamsData
import com.example.newsproject.domain.Mapper

class MapParamsFromCloudToData : Mapper<ParamsCloud, ParamsData> {
    override fun map(from: ParamsCloud)= from.run {
        ParamsData(title = title, param = param)
    }
}