package com.geekbrains.popularlibraries.core.nav

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.geekbrains.popularlibraries.view.user.details.UserDetailsFragment
import com.geekbrains.popularlibraries.view.user.list.UserListFragment
import com.geekbrains.popularlibraries.view.user.repo.details.RepoDetailsFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object AppScreens : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserListFragment.getInstance()
    }

    fun userDetailsScreen(login: String) = FragmentScreen {
        UserDetailsFragment.newInstance(login)
    }

    fun repoDetailsScreen(login: String, repoName: String) = FragmentScreen {
        RepoDetailsFragment.newInstance(login, repoName)
    }

}