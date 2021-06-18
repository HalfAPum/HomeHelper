package com.example.homehelper.models.chat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Letters constructor(
    @SerializedName("letters")
    @Expose
    val letters: ArrayList<Letter>
)