package com.picpay.desafio.android.features.user.di

import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.features.user.presentation.viewmodel.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindEventViewModel(viewmodel: UserViewModel): ViewModel

}