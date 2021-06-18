package com.example.homehelper.di.events

import com.example.homehelper.di.news1.News1Scope
import com.example.homehelper.network.news1.News1Api
import com.example.homehelper.ui.events.list.RecyclerViewAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class EventsModule {

    @EventsScope
    @Provides
    fun provideRecyclerViewAdapter() =
        RecyclerViewAdapter()

    @EventsScope
    @Provides
    fun provideNews1Api(retrofit: Retrofit): News1Api {
        return retrofit.create(News1Api::class.java)
    }
}