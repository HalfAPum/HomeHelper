package com.example.homehelper.di.chat

import com.example.homehelper.ui.chat.apartment.ApartmentFragment
import com.example.homehelper.ui.chat.fundraising.FundraisingFragment
import com.example.homehelper.ui.chat.householder.HouseHolderFragment
import com.example.homehelper.ui.chat.porch.PorchFragment
import com.example.homehelper.ui.events.list.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ChatFragmentsBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesPorchChatFragment() : PorchFragment

    @ContributesAndroidInjector
    abstract fun contributesApartmentChatFragment() : ApartmentFragment

    @ContributesAndroidInjector
    abstract fun contributesHouseHolderChatFragment() : HouseHolderFragment

    @ContributesAndroidInjector
    abstract fun contributesFundraisingChatFragment() : FundraisingFragment
}