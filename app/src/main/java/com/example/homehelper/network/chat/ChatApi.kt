package com.example.homehelper.network.chat

import com.example.homehelper.models.House
import com.example.homehelper.models.Message
import com.example.homehelper.models.NewsEvents1
import com.example.homehelper.models.Token
import com.example.homehelper.models.chat.*
import com.google.gson.JsonObject
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.*

interface ChatApi {

    @GET("api/chat/")
    fun getChats(@Header("Authorization") header: String): Flowable<Chats>

    @GET("api/chat/{path}")
    fun getFullChatInfo(@Header("Authorization") header: String, @Path("path") path: Int): Flowable<MainErrClassChat>

    @POST("api/chat/{path}")
    fun postMessage(
        @Header("Authorization") header: String,
        @Body body: JsonObject,
        @Path("path") path: Int): Call<Message>

    @PUT("api/chat/{path}")
    fun updateMessage(
        @Header("Authorization") header: String,
        @Body body: JsonObject,
        @Path("path") path: Int
    )

    @DELETE("api/chat/{path}")
    fun deleteMessage(
        @Header("Authorization") header: String,
        @Body body: JsonObject,
        @Path("path") path: Int
    )

    //householder api

    @GET("api/letter/")
    fun getLetters(@Header("Authorization") header: String): Flowable<Letters>

    @POST("api/letter/")
    fun postLetter(@Header("Authorization") header: String, @Body body: JsonObject ) : Call<House>
}