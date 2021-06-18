package com.example.homehelper

import androidx.lifecycle.MediatorLiveData
import com.example.homehelper.models.Token
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    @Named("cachedToken")
    var cachedToken: String? = null

}