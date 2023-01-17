package com.geekbrains.popularlibraries.di.modules

import android.content.Context
import androidx.room.Room
import com.geekbrains.popularlibraries.core.database.GithubAppDB
import dagger.Module
import dagger.Provides

const val DB_NAME = "github.db"

@Module
class CacheModule {

    @Provides
    fun db(context: Context): GithubAppDB =
        Room
            .databaseBuilder(context, GithubAppDB::class.java, DB_NAME)
            .build()
}