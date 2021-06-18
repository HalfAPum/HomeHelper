package com.example.homehelper.di.chat

import androidx.lifecycle.ViewModel
import com.example.homehelper.di.ViewModelKey
import com.example.homehelper.ui.auth.AuthViewModel
import com.example.homehelper.ui.chat.apartment.ApartmentViewModel
import com.example.homehelper.ui.chat.fundraising.FundraisingViewModel
import com.example.homehelper.ui.chat.householder.HouseHolderViewModel
import com.example.homehelper.ui.chat.porch.PorchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ChatViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(PorchViewModel::class)
    abstract fun bindPorchViewModel(chatViewModel: PorchViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ApartmentViewModel::class)
    abstract fun bindApartmentViewModel(chatViewModel: ApartmentViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HouseHolderViewModel::class)
    abstract fun bindHouseHolderViewModel(chatViewModel: HouseHolderViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FundraisingViewModel::class)
    abstract fun bindFundraisingViewModel(chatViewModel: FundraisingViewModel) : ViewModel
}