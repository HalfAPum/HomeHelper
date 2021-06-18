package com.example.homehelper.di.chat

import com.example.homehelper.di.auth.AuthScope
import com.example.homehelper.network.auth.AuthApi
import com.example.homehelper.network.chat.ChatApi
import com.example.homehelper.network.profile.ProfileApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ChatModule {

    @ChatScope
    @Provides
    fun provideChatApi(retrofit: Retrofit): ChatApi {
        return retrofit.create(ChatApi::class.java)
    }

    @ChatScope
    @Provides
    fun provideProfileApi(retrofit: Retrofit): ProfileApi {
        return retrofit.create(ProfileApi::class.java)
    }
}