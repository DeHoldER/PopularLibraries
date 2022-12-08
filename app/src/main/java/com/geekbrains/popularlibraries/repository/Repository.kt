package com.geekbrains.popularlibraries.repository

import com.geekbrains.popularlibraries.model.GithubUser

interface GithubRepository {
    fun getUsers(): List<GithubUser>
}