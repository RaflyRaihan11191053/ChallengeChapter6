package com.example.challengechapter5.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.challengechapter6.database.WishlistDao
import com.example.challengechapter6.model.Wishlist

@Database(entities = [User::class, Wishlist::class], version = 3)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun wishlistDao(): WishlistDao

}