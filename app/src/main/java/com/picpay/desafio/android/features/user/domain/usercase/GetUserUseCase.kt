package com.picpay.desafio.android.features.user.domain.usercase

import com.picpay.desafio.android.features.user.domain.repo.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.getUsers()
}