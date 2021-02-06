package com.picpay.desafio.android.features.user.data.mapper

import com.picpay.desafio.android.features.user.domain.model.User
import com.picpay.desafio.android.features.user.data.model.response.UserResponse

object UserMapper {

    fun toUserObject(response: UserResponse): User {
        return User(
            response.img,
            response.name,
            response.id,
            response.username
        )
    }
}