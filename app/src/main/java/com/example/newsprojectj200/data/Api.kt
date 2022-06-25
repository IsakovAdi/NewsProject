package com.example.newsapp.data

import com.example.newsapp.domain.models.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("everything")
    suspend fun getAllNews(
        @Query("q") keyword:String,
        @Query("language") language:String,
        @Query("apiKey") apiKey:String
    ):Response<News>

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country:String,
        @Query("category") category:String,
        @Query("apiKey") apiKey:String
    ):Response<News>

    enum class Languages{
        ar,
        de,
        en,
        es,
        fr,
        he,
        it,
        nl,
        no,
        pt,
        ru,
        se,
        ud,
        zh
    }

    enum class Countries{
        ae,
        ar,
        at,
        au,
        be,
        bg,
        br,
        ca,
        ch,
        cn,
        co,
        cu,
        cz,
        de,
        eg,
        fr,
        gb,
        gr,
        hk,
        hu,
        id,
        ie,
        il,
        it,
        jp,
        kr,
        lt,
        lv,
        ma,
        mx,
        my,
        ng,
        nl,
        no,
        nz,
        ph,
        pl,
        pt,
        ro,
        rs,
        ru,
        sa,
        se,
        sg,
        si,
        sk,
        th,
        tr,
        tw,
        ua,
        us,
        ve,
        za
    }
}