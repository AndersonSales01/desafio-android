package com.picpay.desafio.android.features.user.data.mapper

import com.picpay.desafio.android.features.user.data.database.model.UserPO
import com.picpay.desafio.android.features.user.domain.model.User
import com.picpay.desafio.android.features.user.data.network.model.response.UserResponse
import javax.inject.Inject

class UserMapperImpl @Inject constructor(): UserMapper {

    override fun responseListtoUserList(response: List<UserResponse>): List<User> {
        val userPOList: ArrayList<User> = ArrayList()
        response.map { user ->

                userPOList.add(
                    User(
                        user.img,
                        user.name,
                        user.id,
                        user.username
                    )
                )

        }
        return userPOList
    }
}