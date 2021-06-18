package com.example.homehelper.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User constructor(
    @SerializedName("inhabitant_id")
    @Expose
    val inhabitant_id: Int,
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("password")
    @Expose
    val password: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("second_name")
    @Expose
    val second_name: String,
    @SerializedName("patronymic")
    @Expose
    val patronymic: String,
    @SerializedName("is_online")
    @Expose
    val is_online: Boolean,
    @SerializedName("images")
    @Expose
    var images: List<String>?,
)