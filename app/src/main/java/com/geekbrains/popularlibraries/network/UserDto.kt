package com.geekbrains.popularlibraries.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserDto(
    @Expose
    @SerializedName("id")
    val id: Long = 0,

    @Expose
    @SerializedName("login")
    val login: String = "",

    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String = ""
)