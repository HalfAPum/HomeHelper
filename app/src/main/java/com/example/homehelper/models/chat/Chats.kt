package com.example.homehelper.models.chat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Chats constructor(
    @SerializedName("porch_chat")
    @Expose
    val porch_chat: List<Chat>,
    @SerializedName("apartment_chat")
    @Expose
    val apartment_chat: List<Chat>
)