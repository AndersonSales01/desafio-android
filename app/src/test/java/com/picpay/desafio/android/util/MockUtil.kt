package com.picpay.desafio.android.util

import com.picpay.desafio.android.features.user.data.network.model.response.UserResponse
import com.picpay.desafio.android.features.user.domain.model.User
import retrofit2.Response

object MockUtil {

    fun userListMock() =
        listOf(
            User("https://randomuser.me/api/portraits/men/9.jpg","Eduardo Santos",1001, "@eduardo.santos"),
            User("https://randomuser.me/api/portraits/women/37.jpg","Marina Coelho",1002, "@marina.coelho")

        )
    fun userListResponseMock()  =
        listOf(
            UserResponse("https://randomuser.me/api/portraits/men/9.jpg","Eduardo Santos",1001, "@eduardo.santos"),
            UserResponse("https://randomuser.me/api/portraits/women/37.jpg","Marina Coelho",1002, "@marina.coelho")

        ) as Response<List<UserResponse>>

}