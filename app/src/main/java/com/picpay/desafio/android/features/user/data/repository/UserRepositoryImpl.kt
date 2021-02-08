package com.picpay.desafio.android.features.user.data.repository

import com.picpay.desafio.android.features.user.data.datasource.local.UserLocalDataSource
import com.picpay.desafio.android.features.user.data.datasource.remote.UserRemoteDataSource
import com.picpay.desafio.android.features.user.data.exception.NoConnectivityException
import com.picpay.desafio.android.features.user.domain.model.User
import com.picpay.desafio.android.features.user.data.mapper.UserPOMapper
import com.picpay.desafio.android.features.user.data.mapper.UserMapper
import com.picpay.desafio.android.features.user.domain.repo.UserRepository
import retrofit2.HttpException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private var userRemoteDataSource: UserRemoteDataSource,
    private var userLocalDataSource: UserLocalDataSource
) : UserRepository {

    override suspend fun getUsers(): List<User> {

        val listUser: ArrayList<User> = ArrayList()
        try {
            val response = userRemoteDataSource.getUsersList()

            if (response.isSuccessful) {
                response.let {
                    if (response.body()!!.isNotEmpty()) {

                        for (item in response.body()!!) {

                            val eventObject = UserMapper.toUserObject(item)

                            listUser.add(eventObject)
                        }
                        insertUsersLocalDB(listUser)
                    } else {
                        listUser.addAll(getUserListLocal())
                    }
                }
            }else {
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
        userLocalDataSource.insertUser(UserPOMapper.userListToUserPOList(listUser))
    }

    private suspend fun getUserListLocal(): List<User> {
        var listUser: List<User> = ArrayList()

        val userListLocal = userLocalDataSource.getUserListLocal()

        if (userListLocal.isNotEmpty()) {
            listUser = UserPOMapper.userPOListToUserList(userListLocal)
        }
        return listUser
    }
}