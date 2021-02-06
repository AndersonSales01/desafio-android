package com.picpay.desafio.android.features.user.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserPO(
    @ColumnInfo(name = "image")
    val img: String? = "",
    @ColumnInfo(name = "name")
    val name: String? = "",
    @ColumnInfo(name = "username")
    val username: String? = "",
    @PrimaryKey val id: Int = 0

)