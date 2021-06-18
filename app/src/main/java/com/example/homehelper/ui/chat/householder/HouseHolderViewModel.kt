package com.example.homehelper.ui.chat.householder

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.homehelper.SessionManager
import com.example.homehelper.models.Profile
import com.example.homehelper.models.User
import com.example.homehelper.models.chat.Letter
import com.example.homehelper.models.chat.Letters
import com.example.homehelper.network.chat.ChatApi
import com.example.homehelper.util.Constants
import com.google.gson.JsonObject
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HouseHolderViewModel @Inject constructor(val chatApi: ChatApi, val sessionManager: SessionManager) : ViewModel() {

    lateinit var lettersLiveData: LiveData<ArrayList<Letter>>

    init {
        getLetters()
    }

    fun getLetters() {

        val token = "Bearer ${sessionManager.cachedToken}"
        lettersLiveData = LiveDataReactiveStreams.fromPublisher(
            chatApi.getLetters(token)
                .onErrorReturn {
                    return@onErrorReturn Letters(ArrayList())
                }
                .map ( object : Function<Letters, ArrayList<Letter>> {
                    override fun apply(it: Letters): ArrayList<Letter> {
                        return it.letters
                    }
                })
                .subscribeOn(Schedulers.io())
        )
    }

    fun sendLetter(answer: String) {

        val token = "Bearer ${sessionManager.cachedToken}"
        val body = JsonObject()
        body.addProperty("answer_text", answer)

        chatApi.postLetter(token, body)
    }
}