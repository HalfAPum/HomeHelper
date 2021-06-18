package com.example.homehelper.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.homehelper.SessionManager
import com.example.homehelper.models.Profile
import com.example.homehelper.models.User
import com.example.homehelper.network.profile.ProfileApi
import com.example.homehelper.util.Constants
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfileViewModel @Inject constructor(val profileApi: ProfileApi, val sessionManager: SessionManager) : ViewModel() {
    private lateinit var liveData: LiveData<User>

    init {
        getProfile()
    }

    private fun getProfile() {
        val token = "Bearer ${sessionManager.cachedToken}"

        Log.d(Constants.TAG, token)

        liveData = LiveDataReactiveStreams.fromPublisher(
            profileApi.getProfile(token)
                .onErrorReturn {
                    return@onErrorReturn Profile(
                            User(
                                -1,
                                "-1",
                                "$it",
                                "err",
                                "err",
                                "err",
                                false,
                                listOf("err")
                            )
                        )
                }
                .map ( object : Function<Profile, User> {
                    override fun apply(it: Profile): User {
                        return it.user
                    }
                })
                .subscribeOn(Schedulers.io())
        )
    }

    fun observeUser() = liveData
}