package com.geekbrains.popularlibraries.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForkDto(
    @Expose
    @SerializedName("id")
    val id: Long = 0,

    @Expose
    @SerializedName("name")
    val name: String = "",
)
