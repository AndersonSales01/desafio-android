package com.picpay.desafio.android.features.users.di

import com.picpay.desafio.android.features.users.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.features.users.domain.repo.UserRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository
}