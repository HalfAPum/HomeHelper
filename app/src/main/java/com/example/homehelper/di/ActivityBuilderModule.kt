package com.example.homehelper.di

import com.example.homehelper.di.auth.AuthModule
import com.example.homehelper.di.auth.AuthScope
import com.example.homehelper.di.auth.AuthViewModelsModule
import com.example.homehelper.di.chat.ChatFragmentsBuildersModule
import com.example.homehelper.di.chat.ChatModule
import com.example.homehelper.di.chat.ChatScope
import com.example.homehelper.di.chat.ChatViewModelsModule
import com.example.homehelper.di.events.EventsModule
import com.example.homehelper.di.events.EventsScope
import com.example.homehelper.di.events.EventsViewModelsModule
import com.example.homehelper.di.events.FragmentsBuildersModule
import com.example.homehelper.di.news1.News1Module
import com.example.homehelper.di.news1.News1Scope
import com.example.homehelper.di.news1.News1ViewModelsModule
import com.example.homehelper.di.profile.ProfileModule
import com.example.homehelper.di.profile.ProfileScope
import com.example.homehelper.di.profile.ProfileViewModelsModule
import com.example.homehelper.di.register.RegisterModule
import com.example.homehelper.di.register.RegisterScope
import com.example.homehelper.di.register.RegisterViewModelsModule
import com.example.homehelper.ui.auth.AuthActivity
import com.example.homehelper.ui.chat.ChatActivity
import com.example.homehelper.ui.events.EventsActivity
import com.example.homehelper.ui.news1.News1Activity
import com.example.homehelper.ui.profile.ProfileActivity
import com.example.homehelper.ui.register.RegisterActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ChatScope
    @ContributesAndroidInjector(modules = [
        ChatModule::class,
        ChatViewModelsModule::class,
        ChatFragmentsBuildersModule::class
    ])
    abstract fun contributeChatActivity() : ChatActivity

    @RegisterScope
    @ContributesAndroidInjector(modules = [
        RegisterModule::class,
        RegisterViewModelsModule::class
    ])
    abstract fun contributesRegisterActivity() : RegisterActivity

    @AuthScope
    @ContributesAndroidInjector(modules = [
        AuthModule::class,
        AuthViewModelsModule::class
    ])
    abstract fun contributesAuthActivity() : AuthActivity

    @News1Scope
    @ContributesAndroidInjector(modules = [
        News1Module::class,
        News1ViewModelsModule::class
    ])
    abstract fun contributesNews1Activity() : News1Activity

    @EventsScope
    @ContributesAndroidInjector(modules = [
        EventsModule::class,
        EventsViewModelsModule::class,
        FragmentsBuildersModule::class
    ])
    abstract fun contributeEventsActivity() : EventsActivity

    @ProfileScope
    @ContributesAndroidInjector(modules = [
        ProfileModule::class,
        ProfileViewModelsModule::class
    ])
    abstract fun contributesProfileActivity() : ProfileActivity
}