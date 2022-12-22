package com.geekbrains.popularlibraries.network

import com.geekbrains.popularlibraries.model.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApi {

    @GET("/users")
    fun getAllUsers(): Single<List<GithubUser>>

    @GET("/users/{login}")
    fun getUser(@Path("login") login: String) : Single<GithubUser>
}