package com.picpay.desafio.android.features.user.data.datasource.local

import com.picpay.desafio.android.features.user.data.database.AppDataBase
import com.picpay.desafio.android.features.user.data.database.model.UserPO
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(private var dataBase: AppDataBase ) :
    UserLocalDataSource {

    override suspend fun insertUser(userPOList: List<UserPO>) {
        dataBase.userDao().insertAll(userPOList)
    }

    override suspend fun getUserListLocal(): List<UserPO> {
       return  dataBase.userDao().getUsers()
    }
}