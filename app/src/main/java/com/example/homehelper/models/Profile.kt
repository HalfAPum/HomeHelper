package com.example.homehelper.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Profile  constructor(
    @SerializedName("user")
    @Expose
    val user: User
    )