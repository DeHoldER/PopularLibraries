package com.geekbrains.popularlibraries.core.mapper

import com.geekbrains.popularlibraries.core.database.UserDBObject
import com.geekbrains.popularlibraries.model.GithubUser
import com.geekbrains.popularlibraries.network.dto.UserDto

object UserMapper {

    fun mapToEntity(dto: UserDto): GithubUser {
        return GithubUser(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl,
            reposUrl = dto.reposUrl
        )
    }

    fun mapToEntity(dbObject: UserDBObject): GithubUser {
        return GithubUser(
            id = dbObject.id,
            login = dbObject.login,
            avatarUrl = dbObject.avatarUrl,
            reposUrl = dbObject.reposUrl
        )
    }

    fun mapToDBObject(dto: UserDto): UserDBObject {
        return UserDBObject(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl,
            reposUrl = dto.reposUrl
        )
    }
}
