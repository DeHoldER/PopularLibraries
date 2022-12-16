package com.geekbrains.popularlibraries.repository

import com.geekbrains.popularlibraries.model.GithubUser
import io.reactivex.rxjava3.core.Observable

interface GithubRepository {
    fun getUsers(): Observable<List<GithubUser>>
}