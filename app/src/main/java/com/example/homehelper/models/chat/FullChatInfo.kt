package com.example.homehelper.models.chat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FullChatInfo constructor(
    @SerializedName("chat_description")
    @Expose
    val chat_description : String,
    @SerializedName("chat_image_path")
    @Expose
    val chat_image_path : String,
    @SerializedName("messages")
    @Expose
    val messages : List<HistoryMessage>?,
    @SerializedName("answers")
    @Expose
    val answers : List<HistoryMessage>?,
    @SerializedName("fundraises")
    @Expose
    val fundraises : List<Fundraising>?,
    @SerializedName("inhabitants")
    @Expose
    val inhabitants : List<Inhabitant>?,
    @SerializedName("house_keepers")
    @Expose
    val house_keepers : List<HouseKeeper>?
)