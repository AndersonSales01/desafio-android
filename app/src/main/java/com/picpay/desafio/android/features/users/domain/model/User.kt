package com.picpay.desafio.android.features.users.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val img: String?,
    val name: String?,
    val id: Int?,
    val username: String?
) : Parcelable
