package com.picpay.desafio.android.features.users.di

import com.picpay.desafio.android.features.users.domain.repo.UserRepository
import com.picpay.desafio.android.features.users.domain.usercase.GetUserUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCasesModule {

    @Provides
    @Singleton
    fun providerGetEvents(repository: UserRepository) =  GetUserUseCase(repository)
}