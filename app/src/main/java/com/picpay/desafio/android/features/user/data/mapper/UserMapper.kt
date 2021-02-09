package com.picpay.desafio.android.features.user.data.mapper

import com.picpay.desafio.android.features.user.data.network.model.response.UserResponse
import com.picpay.desafio.android.features.user.domain.model.User

interface UserMapper {
    fun responseListtoUserList(response: List<UserResponse>): List<User>
}