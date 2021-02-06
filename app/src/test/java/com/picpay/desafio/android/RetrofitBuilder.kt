package com.picpay.desafio.android

import com.picpay.desafio.android.features.user.data.network.api.PicPayService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface: PicPayService = getRetrofit().create(PicPayService::class.java)
}