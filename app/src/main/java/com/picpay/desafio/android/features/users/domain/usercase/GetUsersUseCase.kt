package com.picpay.desafio.android.features.users.domain.usercase

import com.picpay.desafio.android.features.users.domain.repo.UserRepository

class GetUsersUseCase(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.getUsers()
}