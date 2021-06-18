package com.example.homehelper.di.news1

import com.example.homehelper.di.auth.AuthScope
import com.example.homehelper.network.auth.AuthApi
import com.example.homehelper.network.news1.News1Api
import com.example.homehelper.ui.news1.News1RecyclerViewAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class News1Module {

        @News1Scope
        @Provides
        fun provideNews1Api(retrofit: Retrofit): News1Api {
            return retrofit.create(News1Api::class.java)
        }

        @News1Scope
        @Provides
        fun provideRecycleViewAdapter() = News1RecyclerViewAdapter()

}