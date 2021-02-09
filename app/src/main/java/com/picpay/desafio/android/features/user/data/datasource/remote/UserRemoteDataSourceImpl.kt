package com.picpay.desafio.android.features.user.data.datasource.remote

import com.picpay.desafio.android.features.user.data.network.api.PicPayService
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(private var endPoint: PicPayService) : UserRemoteDataSource{
    override suspend fun getUsersList()= endPoint.getUsersResponse()
}