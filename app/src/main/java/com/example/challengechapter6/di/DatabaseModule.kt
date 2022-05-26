package com.example.challengechapter6.di

import androidx.room.Room
import com.example.challengechapter5.model.UserDatabase
import com.example.challengechapter6.database.DatabaseHelper
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext().applicationContext,
                UserDatabase::class.java,
            "covidDatabase"
        ).fallbackToDestructiveMigration().build()
    }
    single {
        get<UserDatabase>().wishlistDao()
    }
    single {
        get<UserDatabase>().userDao()
    }
    singleOf(::DatabaseHelper)
}