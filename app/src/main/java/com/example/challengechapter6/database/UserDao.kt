package com.example.challengechapter5.model

import androidx.room.*

@Dao
interface UserDao {
//    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
//    suspend fun login(username: String, password: String): Boolean

    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    suspend fun login(username: String, password: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun register(user: User): Long

    @Update
    suspend fun updateProfile(user: User): Int
}