package com.picpay.desafio.android.util

import android.content.Context
import androidx.room.Room
import com.picpay.desafio.android.features.user.data.database.AppDataBase

object DatabaseMock {

    fun dabatase(context: Context) : AppDataBase {
       return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "picpay"

        ).build()
    }


}