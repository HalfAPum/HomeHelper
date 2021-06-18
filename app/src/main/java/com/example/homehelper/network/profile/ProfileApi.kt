package com.example.homehelper.network.profile

import com.example.homehelper.models.Profile
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileApi {

    @GET("api/profile/")
    fun getProfile(@Header("Authorization") header: String): Flowable<Profile>
}