package com.geekbrains.popularlibraries.di.components

import com.geekbrains.popularlibraries.di.modules.*
import com.geekbrains.popularlibraries.main.MainActivity
import com.geekbrains.popularlibraries.main.MainPresenter
import com.geekbrains.popularlibraries.view.user.details.UserDetailsPresenter
import com.geekbrains.popularlibraries.view.user.list.UserListPresenter
import com.geekbrains.popularlibraries.view.user.repo.details.RepoDetailsPresenter
import dagger.Component

@Component(
    modules = [
        CacheModule::class,
        CiceroneModule::class,
        ContextModule::class,
        NetworkModule::class,
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(userListPresenter: UserListPresenter)
    fun inject(userDetailsPresenter: UserDetailsPresenter)
    fun inject(repoDetailsPresenter: RepoDetailsPresenter)
}