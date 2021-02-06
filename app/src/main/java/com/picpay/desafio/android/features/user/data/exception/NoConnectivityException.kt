package com.picpay.desafio.android.features.user.data.exception

import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String?
        get() = "No Internet Connection";
}