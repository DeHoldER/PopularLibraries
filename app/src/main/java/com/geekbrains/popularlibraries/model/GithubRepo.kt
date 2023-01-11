package com.geekbrains.popularlibraries.model

data class GithubRepo(
    val id: Long,
    val name: String,
    val forksUrl: String,
    val ownerLogin: String
)
