package com.picpay.desafio.android.features.user.data.datasource.local

import com.picpay.desafio.android.features.user.data.database.model.UserPO

interface UserLocalDataSource {

    suspend fun insertUser(userPOList: List<UserPO>): List<Long>

    suspend fun getUserListLocal() : List<UserPO>
}