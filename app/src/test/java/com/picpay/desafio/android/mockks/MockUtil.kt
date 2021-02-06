package com.picpay.desafio.android.mockks

import com.picpay.desafio.android.features.user.domain.model.User

object MockUtil {

    fun userListMock() =
        listOf(
            User("https://randomuser.me/api/portraits/men/9.jpg","Eduardo Santos",1001, "@eduardo.santos"),
            User("https://randomuser.me/api/portraits/women/37.jpg","Marina Coelho",1002, "@marina.coelho")

        )
//    fun userListResponseMock() : Response<List<UserResponse>> =
//        listOf(
//            UserResponse("https://randomuser.me/api/portraits/men/9.jpg","Eduardo Santos",1001, "@eduardo.santos"),
//            UserResponse("https://randomuser.me/api/portraits/women/37.jpg","Marina Coelho",1002, "@marina.coelho")
//
//        )

}