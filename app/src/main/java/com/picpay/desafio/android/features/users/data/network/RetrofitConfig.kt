package com.picpay.desafio.android.features.users.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.features.users.data.network.interceptor.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {

    var retrofit: Retrofit? = null

    private val gson: Gson by lazy { GsonBuilder().create() }

    fun getInstance() : Retrofit {

//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(NetworkConnectionInterceptor())


        if (retrofit == null) {

            retrofit = Retrofit.Builder()
                .baseUrl("http://careers.picpay.com/tests/mobdev/")
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        return retrofit!!

    }
}