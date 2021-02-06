package com.picpay.desafio.android.features.user.data.mapper

import com.picpay.desafio.android.features.user.data.database.model.UserPO
import com.picpay.desafio.android.features.user.domain.model.User

object UserDaoMapper {

    fun userListToUserPOList(userList: List<User>): List<UserPO> {
        val userPOList: ArrayList<UserPO> = ArrayList()
        userList.map { user ->
            if (user.id != null) {
                userPOList.add(
                    UserPO(
                        user.img,
                        user.name,
                        user.username,
                        user.id
                    )
                )
            }
        }
        return userPOList
    }

    fun userPOListToUserList(userPOList: List<UserPO>): List<User> {
        val userList: ArrayList<User> = ArrayList()
        userPOList.map { user ->
            if (user.id != null) {
                userList.add(User(user.img, user.name, user.id, user.username))
            }
        }
        return userList
    }
}