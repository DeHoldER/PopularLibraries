package com.geekbrains.popularlibraries.di.modules

import android.app.Application
import android.content.Context
import com.geekbrains.popularlibraries.app.GeekBrainsApp
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val app: Application) {

    @Provides
    fun app(): Context {
        return app
    }
}