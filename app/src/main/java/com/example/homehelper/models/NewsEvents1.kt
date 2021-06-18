package com.example.homehelper.models

import com.example.homehelper.models.NewEvent
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsEvents1 constructor(
    @SerializedName("news")
    @Expose
    val news: List<NewEvent>
)