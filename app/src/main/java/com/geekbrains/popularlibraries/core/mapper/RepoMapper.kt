package com.geekbrains.popularlibraries.core.mapper

import com.geekbrains.popularlibraries.model.GithubRepo
import com.geekbrains.popularlibraries.network.dto.RepoDto

object RepoMapper {

    fun mapToEntity(dto: RepoDto): GithubRepo {
        return GithubRepo(
            id = dto.id,
            name = dto.name,
            forksUrl = dto.forksUrl,
            ownerLogin = dto.owner.login
        )
    }

}
