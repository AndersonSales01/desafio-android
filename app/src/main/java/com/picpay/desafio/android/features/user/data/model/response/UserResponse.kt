package com.picpay.desafio.android.features.user.data.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String= "",
    @SerializedName("id") val id: Int? = 0,
    @SerializedName("username") val username: String? = ""
)