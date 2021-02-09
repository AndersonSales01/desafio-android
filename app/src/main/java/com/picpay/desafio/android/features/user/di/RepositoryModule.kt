package com.picpay.desafio.android.features.user.di

import com.picpay.desafio.android.features.user.data.datasource.local.UserLocalDataSource
import com.picpay.desafio.android.features.user.data.datasource.local.UserLocalDataSourceImpl
import com.picpay.desafio.android.features.user.data.datasource.remote.UserRemoteDataSource
import com.picpay.desafio.android.features.user.data.datasource.remote.UserRemoteDataSourceImpl
import com.picpay.desafio.android.features.user.data.mapper.UserMapper
import com.picpay.desafio.android.features.user.data.mapper.UserMapperImpl
import com.picpay.desafio.android.features.user.data.mapper.UserPOMapper
import com.picpay.desafio.android.features.user.data.mapper.UserPOMapperImpl
import com.picpay.desafio.android.features.user.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.features.user.domain.repo.UserRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    abstract fun bindUserRemoteDataSource(repositoryImpl: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindUserLocalDataSource(repositoryImpl: UserLocalDataSourceImpl): UserLocalDataSource

    @Binds
    abstract fun bindUserMapper(userMapper: UserMapperImpl): UserMapper

    @Binds
    abstract fun bindUserPOMapper(userPOMapper: UserPOMapperImpl): UserPOMapper


}