package com.geekbrains.popularlibraries.repository.impl

import com.geekbrains.popularlibraries.core.mapper.UserMapper
import com.geekbrains.popularlibraries.model.GithubUser
import com.geekbrains.popularlibraries.network.UserDto
import com.geekbrains.popularlibraries.network.UsersApi
import com.geekbrains.popularlibraries.repository.GithubRepository
import io.reactivex.rxjava3.core.Single

class GithubRepositoryImpl(
    private val usersApi: UsersApi
): GithubRepository {

    override fun getUsers(): Single<List<GithubUser>> {
        return usersApi.getAllUsers()
            .map { it.map(UserMapper::mapToEntity) }
    }

    override fun getUserByLogin(login: String): Single<GithubUser> {
        return usersApi.getUser(login)
            .map(UserMapper::mapToEntity)
    }
}