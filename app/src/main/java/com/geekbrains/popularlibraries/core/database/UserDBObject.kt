package com.geekbrains.popularlibraries.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDBObject(
    @PrimaryKey
    val id: Long,
    val login: String,
    val avatarUrl: String?,
    val reposUrl: String?,
)