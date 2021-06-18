package com.example.homehelper.di.events

import com.example.homehelper.ui.events.calendar.CalendarFragment
import com.example.homehelper.ui.events.list.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesListFragment() : ListFragment

    @ContributesAndroidInjector
    abstract fun contributesCalendarFragment() : CalendarFragment
}