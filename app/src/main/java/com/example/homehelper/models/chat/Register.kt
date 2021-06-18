package com.example.homehelper.models.chat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Register constructor(
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("password")
    @Expose
    val password: String
)