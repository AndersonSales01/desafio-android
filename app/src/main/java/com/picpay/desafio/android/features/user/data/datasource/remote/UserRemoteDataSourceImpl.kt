package com.picpay.desafio.android.features.user.data.datasource.remote

import android.util.Log
import com.picpay.desafio.android.features.user.data.exception.NoConnectivityException
import com.picpay.desafio.android.features.user.data.mapper.UserMapper
import com.picpay.desafio.android.features.user.data.network.api.PicPayService
import com.picpay.desafio.android.features.user.domain.model.User
import java.io.IOException
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(private var endPoint: PicPayService) : UserRemoteDataSource{
//    override suspend fun getUsers(): List<User> {
//        val listUser: ArrayList<User> = ArrayList()
//        try {
//            var response = endPoint.getUsersResponse()
//            //  RetrofitConfig.getInstance().create(PicPayService::class.java).getUsersResponse()
//
//            if (response.isSuccessful) {
//
//                response.let {
//                    if (response.body()!!.isNotEmpty()) {
//
//                        for (item in response.body()!!) {
//
//                            val eventObject = UserMapper.toUserObject(item)
//
//                            listUser.add(eventObject)
//                        }
//                    }
//                }
//            }
//
//        } catch (e: NoConnectivityException) {
//            Log.d("Users", "message: $e")
//        } catch (e: IOException) {
//
//        }
//
//        Log.d("Users", "list $listUser")
//
//        return listUser
//    }

    override suspend fun getUsersList()= endPoint.getUsersResponse()
}