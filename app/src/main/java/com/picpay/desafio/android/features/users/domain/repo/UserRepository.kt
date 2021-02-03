package com.picpay.desafio.android.features.users.domain.repo

import com.picpay.desafio.android.features.users.domain.model.User

interface UserRepository {

    suspend fun getUsers() : List<User>
}