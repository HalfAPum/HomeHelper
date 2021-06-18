package com.example.homehelper.network.news1

import com.example.homehelper.models.NewsEvents1
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Header

interface News1Api {

    @GET("api/news/")
    fun getNews(@Header("Authorization") header: String): Flowable<NewsEvents1>
}