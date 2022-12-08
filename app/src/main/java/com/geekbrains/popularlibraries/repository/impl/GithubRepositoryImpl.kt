package com.geekbrains.popularlibraries.repository.impl

import com.geekbrains.popularlibraries.model.GithubUser
import com.geekbrains.popularlibraries.repository.GithubRepository

class GithubRepositoryImpl : GithubRepository {

    private val repositories = listOf(
        GithubUser("MrFox"),
        GithubUser("VictorMelnik"),
        GithubUser("Denix"),
        GithubUser("DmitryWb"),
        GithubUser("Larisa")
    )

    override fun getUsers(): List<GithubUser> {
        return repositories
    }
}