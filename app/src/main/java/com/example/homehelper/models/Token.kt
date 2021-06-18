package com.example.homehelper.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Token constructor(
    @SerializedName("inhabitant_token")
    @Expose
    val token: String
)