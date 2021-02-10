package com.picpay.desafio.android.features.user.data.network.interceptor

import com.picpay.desafio.android.features.user.data.exception.NoConnectivityException
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MyInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!ConnectionTest.verifyConectation())
            throw NoConnectivityException()

        val response: Response = chain.proceed(chain.request())
        val cacheControl: CacheControl = CacheControl.Builder()
            .maxAge(10, TimeUnit.DAYS)
            .build()
        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()

    }
}