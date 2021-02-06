package com.picpay.desafio.android.features.user.data.database

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {

    private var INSTANCE: NewDataBase? = null

    fun getInstance(context: Context): NewDataBase {
        if (INSTANCE == null) {
            synchronized(NewDataBase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            NewDataBase::class.java,
            "picpay"
        ).build()
}