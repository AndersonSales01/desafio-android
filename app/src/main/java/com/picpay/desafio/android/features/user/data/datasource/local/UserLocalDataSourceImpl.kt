package com.picpay.desafio.android.features.user.data.datasource.local

import android.content.Context
import android.util.Log
import com.picpay.desafio.android.features.user.data.database.DatabaseBuilder
import com.picpay.desafio.android.features.user.data.database.model.UserPO
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(private var context: Context) :
    UserLocalDataSource {

    override suspend fun insertUser(userPOList: List<UserPO>) {
        DatabaseBuilder.getInstance(context).userDao().insertAll(userPOList)
        Log.d("database", "Size insertUser: " + DatabaseBuilder.getInstance(context).userDao().getUsers().size)
    }

    override suspend fun getUserListLocal(): List<UserPO> {
        Log.d("database", "Size getUserListLocal: " + DatabaseBuilder.getInstance(context).userDao().getUsers().size)
       return DatabaseBuilder.getInstance(context).userDao().getUsers()
    }
}