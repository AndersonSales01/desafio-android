package com.picpay.desafio.android.features.users.data.network.interceptor

import kotlinx.coroutines.*
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

object ConnectionTest {
    fun verifyConectation(): Boolean {
        var isConnect = false
        try {
            val sock = Socket()
            sock.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            sock.close()
            isConnect = true
        } catch (e: IOException) {
            isConnect = false
        }
        return isConnect
    }
}