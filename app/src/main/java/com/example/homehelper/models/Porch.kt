package com.example.homehelper.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Porch constructor(
    @SerializedName("porch_id")
    @Expose
    val id: Int
)