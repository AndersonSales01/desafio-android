package com.picpay.desafio.android.util

import com.picpay.desafio.android.features.user.data.database.model.UserPO
import com.picpay.desafio.android.features.user.data.network.model.response.UserResponse
import com.picpay.desafio.android.features.user.domain.model.User
import retrofit2.Response

object MockUtil {

    fun userListMock() =
        listOf(
            User("https://randomuser.me/api/portraits/men/9.jpg","Eduardo Santos",1001, "@eduardo.santos"),
            User("https://randomuser.me/api/portraits/women/37.jpg","Marina Coelho",1002, "@marina.coelho")

        )
    fun userResponseListMock()  =
        listOf(
            UserResponse("https://randomuser.me/api/portraits/men/9.jpg","Eduardo Santos",1001, "@eduardo.santos"),
            UserResponse("https://randomuser.me/api/portraits/women/37.jpg","Marina Coelho",1002, "@marina.coelho")

        )

    fun userPOListMock()  =
        listOf(
            UserPO("https://randomuser.me/api/portraits/men/9.jpg","Eduardo Santos", "@eduardo.santos",1001),
            UserPO("https://randomuser.me/api/portraits/women/37.jpg","Marina Coelho", "@marina.coelho",1002)

        )

    fun mockUserObject() = User("https://randomuser.me/api/portraits/men/9.jpg","Eduardo Santos",1001, "@eduardo.santos")
    fun mockObjectResponse() = UserResponse("https://randomuser.me/api/portraits/women/37.jpg","Marina Coelho",1002, "@marina.coelho")
}