package com.example.homehelper.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class House constructor(
    @SerializedName("house_id")
    @Expose
    val house_id: Int
)