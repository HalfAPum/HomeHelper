package com.example.homehelper.di.profile

import androidx.lifecycle.ViewModel
import com.example.homehelper.di.ViewModelKey
import com.example.homehelper.ui.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProfileViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(authViewModel: ProfileViewModel) : ViewModel
}