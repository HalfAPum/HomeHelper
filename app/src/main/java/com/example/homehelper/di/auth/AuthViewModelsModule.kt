package com.example.homehelper.di.auth

import androidx.lifecycle.ViewModel
import com.example.homehelper.di.ViewModelKey
import com.example.homehelper.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel) : ViewModel
}