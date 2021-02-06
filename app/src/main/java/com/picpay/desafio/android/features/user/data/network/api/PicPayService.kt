package com.picpay.desafio.android.features.user.data.network.api

import com.picpay.desafio.android.features.user.domain.model.User
import com.picpay.desafio.android.features.user.data.model.response.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<User>>
    @GET("users")
   suspend fun getUsersResponse(): Response<List<UserResponse>>
}