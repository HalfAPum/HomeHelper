package com.example.homehelper.models.chat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HistoryMessage constructor(
    @SerializedName("message_id")
    @Expose
    val message_id : Int,
    @SerializedName("user_id")
    @Expose
    val user_id : Int,
    @SerializedName("time_receive")
    @Expose
    val time_receive : String,
    @SerializedName("text")
    @Expose
    val text : String,
    @SerializedName("is_house_keeper")
    @Expose
    val is_house_keeper : Boolean
)