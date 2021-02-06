package com.picpay.desafio.android.features.user.di

import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.features.user.data.network.api.PicPayService
import com.picpay.desafio.android.features.user.data.network.interceptor.MyInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetWorkModule {

    @Provides
    @Singleton
    fun EndPoint(retroFit: Retrofit): PicPayService {
        return retroFit.create<PicPayService>(PicPayService::class.java)
    }

    @Provides
    fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideOkHttpClient(interceptor: MyInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor).build()
    }
}