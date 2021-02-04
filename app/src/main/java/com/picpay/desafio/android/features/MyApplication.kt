package com.picpay.desafio.android.features

import android.app.Application
import com.picpay.desafio.android.features.users.di.AppComponent
import com.picpay.desafio.android.features.users.di.DaggerAppComponent

class MyApplication : Application() {
    lateinit var  appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}