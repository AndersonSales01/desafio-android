package com.picpay.desafio.android.features.user.data.repository

import com.picpay.desafio.android.features.user.data.datasource.local.UserLocalDataSource
import com.picpay.desafio.android.features.user.data.datasource.remote.UserRemoteDataSource
import com.picpay.desafio.android.features.user.data.exception.NoConnectivityException
import com.picpay.desafio.android.features.user.data.mapper.UserMapper
import com.picpay.desafio.android.features.user.domain.model.User
import com.picpay.desafio.android.features.user.data.mapper.UserPOMapperImpl
import com.picpay.desafio.android.features.user.data.mapper.UserMapperImpl
import com.picpay.desafio.android.features.user.data.mapper.UserPOMapper
import com.picpay.desafio.android.features.user.domain.repo.UserRepository
import retrofit2.HttpException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
    private val userMapper: UserMapper,
    private val userPOMapper: UserPOMapper
) : UserRepository {

    override suspend fun getUsers(): List<User> {

        val listUser: ArrayList<User> = ArrayList()
        try {
            val response = userRemoteDataSource.getUsersList()

            if (response.isSuccessful) {
                response.let {
                    if (response.body()!!.isNotEmpty()) {

                        listUser.addAll(userMapper.responseListtoUserList(response.body()!!))
                        insertUsersLocalDB(listUser)
                    } else {
                        listUser.addAll(getUserListLocal())
                    }
                }
            } else {
                listUser.addAll(getUserListLocal())
            }
        } catch (e: NoConnectivityException) {
            listUser.addAll(getUserListLocal())
        } catch (e: HttpException) {
            listUser.addAll(getUserListLocal())
        }
        return listUser
    }

    private suspend fun insertUsersLocalDB(listUser: List<User>) {
        userLocalDataSource.insertUser(userPOMapper.userListToUserPOList(listUser))
    }

    private suspend fun getUserListLocal(): List<User> {
        var listUser: List<User> = ArrayList()

        val userListLocal = userLocalDataSource.getUserListLocal()

        if (userListLocal.isNotEmpty()) {
            listUser = userPOMapper.userPOListToUserList(userListLocal)
        }
        return listUser
    }
}