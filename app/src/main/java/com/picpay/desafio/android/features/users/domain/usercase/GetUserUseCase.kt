package com.picpay.desafio.android.features.users.domain.usercase

import com.picpay.desafio.android.features.users.domain.repo.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.getUsers()
}