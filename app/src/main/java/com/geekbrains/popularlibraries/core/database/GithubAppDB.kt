package com.geekbrains.popularlibraries.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserDBObject::class],
    version = 1
)
abstract class GithubAppDB : RoomDatabase() {

    abstract fun userDao(): UserDAO

    companion object {
        fun create(context: Context): GithubAppDB {
            return Room.databaseBuilder(
                context,
                GithubAppDB::class.java,
                "github.db"
            ).build()
        }
    }

}