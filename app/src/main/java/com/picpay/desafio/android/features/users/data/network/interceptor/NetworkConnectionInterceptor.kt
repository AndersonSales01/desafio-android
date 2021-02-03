package com.picpay.desafio.android.features.users.data.network.interceptor

import com.picpay.desafio.android.features.users.data.exception.NoConnectivityException
import com.picpay.desafio.android.features.users.data.network.ConnectionTest
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkConnectionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!ConnectionTest.verifyConectation())
            throw NoConnectivityException()
            // Throwing our custom exception 'NoConnectivityException'

        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}