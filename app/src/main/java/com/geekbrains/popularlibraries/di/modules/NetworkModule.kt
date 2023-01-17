package com.geekbrains.popularlibraries.di.modules

import android.content.Context
import com.geekbrains.popularlibraries.BuildConfig
import com.geekbrains.popularlibraries.network.AndroidNetworkStatus
import com.geekbrains.popularlibraries.network.INetworkStatus
import com.geekbrains.popularlibraries.network.UsersApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

const val BASE_URL = "BASE_URL"

@Module
class NetworkModule {

    @Provides
    @Named(BASE_URL)
    fun baseUrl(): String {
        return BuildConfig.SERVER_URL
    }

    @Provides
    fun usersApi(retrofit: Retrofit): UsersApi {
        return createRetrofit(BASE_URL, createGson()).create(UsersApi::class.java)
//        createRetrofit().create<UsersApi>()
    }

    @Provides
    fun createGson(): Gson = GsonBuilder()
//        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Provides
    fun createRetrofit(
        @Named(BASE_URL) baseUrl: String,
        gson: Gson,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun networkStatus(context: Context): INetworkStatus {
        return AndroidNetworkStatus(context)
    }
}