package com.picpay.desafio.android.features.user.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.features.user.data.database.dao.UserDao
import com.picpay.desafio.android.features.user.data.database.model.UserPO

@Database(entities = arrayOf(UserPO::class), version = 1)
abstract class NewDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

}