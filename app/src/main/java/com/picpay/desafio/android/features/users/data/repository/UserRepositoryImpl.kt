package com.picpay.desafio.android.features.users.data.repository

import android.util.Log
import com.picpay.desafio.android.features.users.domain.model.User
import com.picpay.desafio.android.features.users.data.mapper.UserMapper
import com.picpay.desafio.android.features.users.data.exception.NoConnectivityException
import com.picpay.desafio.android.features.users.data.network.api.PicPayService
import com.picpay.desafio.android.features.users.domain.repo.UserRepository
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private var endPoint: PicPayService): UserRepository {

    override suspend fun getUsers(): List<User> {

        val listUser: ArrayList<User> = ArrayList()
        try {
            var response = endPoint.getUsersResponse()
              //  RetrofitConfig.getInstance().create(PicPayService::class.java).getUsersResponse()

            if (response.isSuccessful) {

                response.let {
                    if (response.body()!!.isNotEmpty()) {

                        for (item in response.body()!!) {

                            val eventObject = UserMapper.toUserObject(item)

                            listUser.add(eventObject)
                        }
                    }
                }
            }

        } catch (e: NoConnectivityException) {
            Log.d("Users", "message: $e")
        } catch (e: IOException) {

        }

        Log.d("Users", "list $listUser")

        return listUser
    }
}