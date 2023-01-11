package com.geekbrains.popularlibraries.network.dto

import com.geekbrains.popularlibraries.model.GithubUser
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RepoDto(
    @Expose
    @SerializedName("id")
    val id: Long = 0,

    @Expose
    @SerializedName("name")
    val name: String = "",

    @Expose
    @SerializedName("forks_url")
    val forksUrl: String = "",

    @Expose
    @SerializedName("owner")
    val owner: UserDto,
)
