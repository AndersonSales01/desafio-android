package com.picpay.desafio.android.features.user.di

import android.content.Context
import com.picpay.desafio.android.features.user.presentation.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, UseCasesModule::class, ViewModelBuilderModule::class, ViewModelModule::class,NetWorkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }


    fun inject(activity: MainActivity)

}