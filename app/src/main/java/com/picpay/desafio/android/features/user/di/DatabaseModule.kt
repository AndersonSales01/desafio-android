package com.picpay.desafio.android.features.user.di

import android.content.Context
import androidx.room.Room
import com.picpay.desafio.android.features.user.data.database.AppDataBase
import com.picpay.desafio.android.features.user.data.database.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase( context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "picpay").build()
    }

    @Singleton
    @Provides
    fun providesProductDao(demoDatabase: AppDataBase): UserDao {
        return demoDatabase.userDao()
    }

}