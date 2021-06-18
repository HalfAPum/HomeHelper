package com.example.homehelper.models

import android.graphics.Bitmap
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewEvent(
    @SerializedName("images")
    @Expose
    val images: List<String>?,
    @SerializedName("title")
    @Expose
    val header: String,
    @SerializedName("creation_date")
    @Expose
    val date: String,
    @SerializedName("text")
    @Expose
    val text: String,
    @SerializedName("date_start")
    @Expose
    val dateStart: String?,
    @SerializedName("date_end")
    @Expose
    val dateEnd: String?
)