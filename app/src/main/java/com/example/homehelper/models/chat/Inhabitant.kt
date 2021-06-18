package com.example.homehelper.models.chat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Inhabitant constructor(
    @SerializedName("inhabitant_id")
    @Expose
    val inhabitant_id: Int,
    @SerializedName("name")
    @Expose
    val name : String,
    @SerializedName("second_name")
    @Expose
    val second_name : String,
    @SerializedName("is_online")
    @Expose
    val is_online : Boolean,
    @SerializedName("images")
    @Expose
    val images : String?
)