package com.picpay.desafio.android.features.user.data.mapper

import com.picpay.desafio.android.features.user.data.database.model.UserPO
import com.picpay.desafio.android.features.user.domain.model.User

interface UserPOMapper {
    fun userListToUserPOList(userList: List<User>): List<UserPO>
    fun userPOListToUserList(userPOList: List<UserPO>): List<User>
}