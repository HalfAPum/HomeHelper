package com.example.homehelper.models.chat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Fundraising constructor(
    @SerializedName("time_start")
    @Expose
    val time_start : String,
    @SerializedName("time_end")
    @Expose
    val time_end : String,
    @SerializedName("current_value")
    @Expose
    val current_value : Int,
    @SerializedName("needed_value")
    @Expose
    val needed_value : Int,
    @SerializedName("recommended_value")
    @Expose
    val recommended_value : Int,
    @SerializedName("fundraising_text")
    @Expose
    val fundraising_text : String
    )