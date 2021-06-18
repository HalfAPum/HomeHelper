package com.example.homehelper.di.register

import androidx.lifecycle.ViewModel
import com.example.homehelper.di.ViewModelKey
import com.example.homehelper.ui.auth.AuthViewModel
import com.example.homehelper.ui.register.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RegisterViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(authViewModel: RegisterViewModel) : ViewModel
}