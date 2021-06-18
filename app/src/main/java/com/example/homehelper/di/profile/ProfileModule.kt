package com.example.homehelper.di.profile

import com.example.homehelper.di.news1.News1Scope
import com.example.homehelper.network.news1.News1Api
import com.example.homehelper.network.profile.ProfileApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ProfileModule {

    @ProfileScope
    @Provides
    fun provideProfileApi(retrofit: Retrofit): ProfileApi{
        return retrofit.create(ProfileApi::class.java)
    }

}