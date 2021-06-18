package com.example.homehelper.ui.auth

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.homehelper.models.Token
import com.example.homehelper.network.auth.AuthApi
import com.google.gson.JsonObject
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel @Inject constructor(val authApi: AuthApi) : ViewModel(){

    var loginObserver: LiveData<Token>? = null

    fun tryLogin(email: String, password: String) {
        //check fields
        if(TextUtils.isEmpty(email)) return
        if(TextUtils.isEmpty(password)) return

        //create json object
        val jsonObject = JsonObject()
        jsonObject.addProperty("email", email)
        jsonObject.addProperty("password", password)

        //
        loginObserver = LiveDataReactiveStreams.fromPublisher(
            authApi.postSingIn(jsonObject)
                .onErrorReturn {
                    return@onErrorReturn Token("err")
                }
                .subscribeOn(Schedulers.io())
        )
    }

}