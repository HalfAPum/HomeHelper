package com.example.homehelper.di.register

import com.example.homehelper.di.auth.AuthScope
import com.example.homehelper.network.auth.AuthApi
import com.example.homehelper.network.register.RegisterApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RegisterModule {

    @RegisterScope
    @Provides
    fun provideRegisterApi(retrofit: Retrofit): RegisterApi {
        return retrofit.create(RegisterApi::class.java)
    }
}