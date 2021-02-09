package com.picpay.desafio.android.features.user.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class User(
    val img: String?,
    val name: String?,
    val id: Int?,
    val username: String?
)
