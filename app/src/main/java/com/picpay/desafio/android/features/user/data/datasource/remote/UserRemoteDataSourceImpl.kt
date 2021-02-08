package com.picpay.desafio.android.features.user.data.datasource.remote

import android.util.Log
import com.picpay.desafio.android.features.user.data.exception.NoConnectivityException
import com.picpay.desafio.android.features.user.data.mapper.UserMapper
import com.picpay.desafio.android.features.user.data.network.api.PicPayService
import com.picpay.desafio.android.features.user.domain.model.User
import java.io.IOException
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(private var endPoint: PicPayService) : UserRemoteDataSource{
    override suspend fun getUsersList()= endPoint.getUsersResponse()
}