package com.picpay.desafio.android.features.users.data.exception

import java.io.IOError
import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String?
        get() = "No Internet Connection";
}