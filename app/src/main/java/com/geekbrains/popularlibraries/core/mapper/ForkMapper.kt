package com.geekbrains.popularlibraries.core.mapper

import com.geekbrains.popularlibraries.model.GithubFork
import com.geekbrains.popularlibraries.network.dto.ForkDto

object ForkMapper {

    fun mapToEntity(dto: ForkDto): GithubFork {
        return GithubFork(
            id = dto.id,
            name = dto.name,
        )
    }

}
