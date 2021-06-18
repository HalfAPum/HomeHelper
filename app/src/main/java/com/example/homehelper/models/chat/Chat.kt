package com.example.homehelper.models.chat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Chat constructor(
    @SerializedName("chat_id")
    @Expose
    val chat_id: Int
)