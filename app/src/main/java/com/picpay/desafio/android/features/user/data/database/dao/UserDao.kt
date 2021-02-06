package com.picpay.desafio.android.features.user.data.database.dao

import androidx.room.*
import com.picpay.desafio.android.features.user.data.database.model.UserPO

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserPO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<UserPO>) : List<Long>

    @Update
    fun update(user: UserPO): Int

    @Delete
    fun delete(user: UserPO)

    @Query("SELECT * FROM User")
    fun getUsers(): List<UserPO>
}