package com.geekbrains.popularlibraries.core.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userDBObject: UserDBObject): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userDBObject: List<UserDBObject>): Completable

    @Query("SELECT * FROM users")
    fun getAll(): List<UserDBObject>

    @Query("SELECT * FROM users")
    fun queryForAllUsers(): Single<List<UserDBObject>>

    @Delete
    fun delete(userDBObject: UserDBObject): Completable
}