package com.geekbrains.popularlibraries.repository

import com.geekbrains.popularlibraries.model.GithubFork
import com.geekbrains.popularlibraries.model.GithubRepo
import com.geekbrains.popularlibraries.model.GithubUser
import io.reactivex.rxjava3.core.Single

interface GithubRepository {

    fun getUsers(): Single<List<GithubUser>>

    fun getUserByLogin(login: String): Single<GithubUser>

    fun getReposByLogin(login: String): Single<List<GithubRepo>>

    fun getRepo(login: String, repoName: String): Single<GithubRepo>

    fun getForks(login: String, repoName: String): Single<List<GithubFork>>

}