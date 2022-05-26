package com.example.challengechapter6.di

import com.example.challengechapter6.datastore.UserManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataStoreModule = module {
    singleOf(::UserManager)
}