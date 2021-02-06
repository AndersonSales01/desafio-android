package com.picpay.desafio.android.features.user.domain.repo

import com.picpay.desafio.android.features.user.domain.model.User

interface UserRepository {

    suspend fun getUsers() : List<User>
}