package com.picpay.desafio.android.features.user.data.datasource.remote

import com.picpay.desafio.android.features.user.data.model.response.UserResponse

import retrofit2.Response

interface UserRemoteDataSource {
     suspend fun getUsersList(): Response<List<UserResponse>>

}