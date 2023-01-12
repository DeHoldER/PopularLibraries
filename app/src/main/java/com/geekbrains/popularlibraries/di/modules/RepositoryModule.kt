package com.geekbrains.popularlibraries.di.modules

import com.geekbrains.popularlibraries.core.database.UserDAO
import com.geekbrains.popularlibraries.network.UsersApi
import com.geekbrains.popularlibraries.repository.GithubRepository
import com.geekbrains.popularlibraries.repository.impl.GithubRepositoryImpl
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Single


//1:14
@Module
class RepositoryModule {

    @Provides
    fun userListRepo(
        usersApi: UsersApi,
        userDAO: UserDAO,
        networkStatus: Single<Boolean>
    ): GithubRepository {
        return GithubRepositoryImpl(usersApi, userDAO, networkStatus)
    }

}