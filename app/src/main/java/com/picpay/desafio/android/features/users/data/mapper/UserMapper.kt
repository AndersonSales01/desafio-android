package com.picpay.desafio.android.features.users.data.mapper

import com.picpay.desafio.android.features.users.domain.model.User
import com.picpay.desafio.android.features.users.data.model.response.UserResponse

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