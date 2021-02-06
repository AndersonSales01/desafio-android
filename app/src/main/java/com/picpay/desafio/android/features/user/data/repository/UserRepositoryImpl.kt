package com.picpay.desafio.android.features.user.data.repository

import android.util.Log
import com.picpay.desafio.android.features.user.data.datasource.local.UserLocalDataSource
import com.picpay.desafio.android.features.user.data.datasource.remote.UserRemoteDataSource
import com.picpay.desafio.android.features.user.data.exception.NoConnectivityException
import com.picpay.desafio.android.features.user.domain.model.User
import com.picpay.desafio.android.features.user.data.mapper.UserDaoMapper
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
            var response = userRemoteDataSource.getUsersList()
            // listUser = userRemoteDataSource.getUsers()
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
        userLocalDataSource.insertUser(UserDaoMapper.userListToUserPOList(listUser))
    }

    private suspend fun getUserListLocal(): List<User> {
        var listUser: List<User> = ArrayList()
        Log.d("database", "Pegar lista local ")
        val userListLocal = userLocalDataSource.getUserListLocal()
        Log.d("database", "Lista Local:  $userListLocal ")
        if (userListLocal.isNotEmpty()) {
            listUser = UserDaoMapper.userPOListToUserList(userListLocal)

            Log.d("database", "Lista Local Após mapper:  $listUser ")
        }
        return listUser
    }
}