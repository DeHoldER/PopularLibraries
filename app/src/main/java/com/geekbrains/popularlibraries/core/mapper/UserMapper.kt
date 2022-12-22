package com.geekbrains.popularlibraries.core.mapper

import com.geekbrains.popularlibraries.model.GithubUser
import com.geekbrains.popularlibraries.network.UserDto

object UserMapper {

    fun mapToEntity(dto: UserDto): GithubUser {
        return GithubUser(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl
        )
    }
}
