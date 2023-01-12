package com.geekbrains.popularlibraries.repository.impl

import com.geekbrains.popularlibraries.core.database.UserDAO
import com.geekbrains.popularlibraries.core.mapper.ForkMapper
import com.geekbrains.popularlibraries.core.mapper.RepoMapper
import com.geekbrains.popularlibraries.core.mapper.UserMapper
import com.geekbrains.popularlibraries.model.GithubFork
import com.geekbrains.popularlibraries.model.GithubRepo
import com.geekbrains.popularlibraries.model.GithubUser
import com.geekbrains.popularlibraries.network.UsersApi
import com.geekbrains.popularlibraries.repository.GithubRepository
import com.geekbrains.popularlibraries.utils.doCompletableIf
import io.reactivex.rxjava3.core.Single

class GithubRepositoryImpl(
    private val usersApi: UsersApi,
    private val userDAO: UserDAO,
    private val networkStatus: Single<Boolean>
) : GithubRepository {

    override fun getUsers(): Single<List<GithubUser>> {
//        return fetchFromApi(true)
        return networkStatus.flatMap { hasConnection ->
            if (hasConnection) {
                fetchFromApi(true)
            } else
                getFromDb()
        }
    }

    override fun getUserByLogin(login: String): Single<GithubUser> {
        return usersApi.getUser(login)
            .map(UserMapper::mapToEntity)
    }

    override fun getReposByLogin(login: String): Single<List<GithubRepo>> {
        return usersApi.getUserRepos(login)
            .map { it.map(RepoMapper::mapToEntity) }
    }

    override fun getRepo(login: String, repoName: String): Single<GithubRepo> {
        return usersApi.getRepo(login, repoName)
            .map(RepoMapper::mapToEntity)
    }

    override fun getForks(login: String, repoName: String): Single<List<GithubFork>> {
        return usersApi.getForks(login, repoName)
            .map { it.map(ForkMapper::mapToEntity) }
    }

    private fun fetchFromApi(shouldPersist: Boolean): Single<List<GithubUser>> {
        return usersApi.getAllUsers()
            .doCompletableIf(shouldPersist) {
                userDAO.insertAll(it.map(UserMapper::mapToDBObject))
            }
            .map { it.map(UserMapper::mapToEntity) }
    }

    private fun getFromDb(): Single<List<GithubUser>> {
        return userDAO.queryForAllUsers().map { it.map(UserMapper::mapToEntity) }
    }

//ReposMapper - 37:30
}