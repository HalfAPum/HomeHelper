package com.example.homehelper.network.auth

import com.example.homehelper.models.Token
import com.google.gson.JsonObject
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("inhabitant-auth/sign-in")
    fun postSingIn(@Body body: JsonObject): Flowable<Token>
}