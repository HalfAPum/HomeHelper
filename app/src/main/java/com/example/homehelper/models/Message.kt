package com.example.homehelper.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Message constructor(@SerializedName("message_id")
                          @Expose
                          val message_id: Int) {
}