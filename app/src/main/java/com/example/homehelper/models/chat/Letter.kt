package com.example.homehelper.models.chat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Letter constructor(
    @SerializedName("letter_id")
    @Expose
    val letter_id : Int,
    @SerializedName("answer_text")
    @Expose
    val answer_text : String,
    @SerializedName("reply_text")
    @Expose
    val reply_text : String?,
    @SerializedName("reply_type")
    @Expose
    val reply_type : Boolean?
)