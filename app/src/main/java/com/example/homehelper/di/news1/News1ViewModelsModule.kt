package com.example.homehelper.di.news1

import androidx.lifecycle.ViewModel
import com.example.homehelper.di.ViewModelKey
import com.example.homehelper.ui.auth.AuthViewModel
import com.example.homehelper.ui.news1.News1ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class News1ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(News1ViewModel::class)
    abstract fun bindNews1ViewModel(news1ViewModel: News1ViewModel) : ViewModel

}