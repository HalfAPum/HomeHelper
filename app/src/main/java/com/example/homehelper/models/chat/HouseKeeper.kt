package com.example.homehelper.models.chat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HouseKeeper constructor(
    @SerializedName("nickname")
    @Expose
    val nickname: String
)