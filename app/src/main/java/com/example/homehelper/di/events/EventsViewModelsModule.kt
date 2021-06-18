package com.example.homehelper.di.events

import androidx.lifecycle.ViewModel
import com.example.homehelper.di.ViewModelKey
import com.example.homehelper.ui.events.list.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class EventsViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(listViewModel: ListViewModel) : ViewModel


}