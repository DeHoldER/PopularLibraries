package com.geekbrains.popularlibraries.network

import com.geekbrains.popularlibraries.network.dto.ForkDto
import com.geekbrains.popularlibraries.network.dto.RepoDto
import com.geekbrains.popularlibraries.network.dto.UserDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApi {

    @GET("/users")
    fun getAllUsers(): Single<List<UserDto>>

    @GET("/users/{login}")
    fun getUser(@Path("login") login: String): Single<UserDto>

    @GET("/users/{login}/repos")
    fun getUserRepos(@Path("login") login: String): Single<List<RepoDto>>

    @GET("/repos/{login}/{repoName}")
    fun getRepo(
        @Path("login") login: String,
        @Path("repoName") repoName: String
    ): Single<RepoDto>

    @GET("/repos/{login}/{repoName}/forks")
    fun getForks(
        @Path("login") login: String,
        @Path("repoName") repoName: String
    ): Single<List<ForkDto>>

}
