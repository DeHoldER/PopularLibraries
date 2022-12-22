package com.geekbrains.popularlibraries.repository

import com.geekbrains.popularlibraries.model.GithubUser
import io.reactivex.rxjava3.core.Single

interface GithubRepository {

    fun getUsers(): Single<List<GithubUser>>

    fun getUserByLogin(login: String) : Single<GithubUser>
}