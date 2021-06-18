package com.example.homehelper.network.register

import com.example.homehelper.models.House
import com.example.homehelper.models.Porch
import com.example.homehelper.models.Profile
import com.example.homehelper.models.chat.Register
import com.google.gson.JsonObject
import io.reactivex.Flowable
import retrofit2.http.*

interface RegisterApi {

    @POST("api/house/check-house/")
    fun getHouseIdByName(@Body body: JsonObject): Flowable<House>

    @GET("api/porch/{path}")
    fun getPorches(@Path("path") path: Int): Flowable<List<Porch>>

    @POST("inhabitant-auth/sign-up")
    fun postUser(@Body body: JsonObject) : Flowable<Register>
}