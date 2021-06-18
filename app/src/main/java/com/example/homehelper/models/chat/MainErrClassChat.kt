package com.example.homehelper.models.chat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MainErrClassChat constructor(
    @SerializedName("chat")
    @Expose
    val info: FullChatInfo
)