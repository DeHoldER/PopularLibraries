package com.geekbrains.popularlibraries.repository.impl

import com.geekbrains.popularlibraries.model.GithubUser
import com.geekbrains.popularlibraries.repository.GithubRepository
import io.reactivex.rxjava3.core.Single

class GithubRepositoryImpl : GithubRepository {

    private val repositories = listOf(
        GithubUser(login = "MrFox"),
        GithubUser(login = "VictorMelnik"),
        GithubUser(login = "Denix"),
        GithubUser(login = "DmitryWb"),
        GithubUser(login = "Larisa")
    )

    //    override fun getUsers(): Single<List<GithubUser>> = Single.just(repositories)
    override fun getUsers(): Single<List<GithubUser>> = Single.create { it.onSuccess(repositories) }

}