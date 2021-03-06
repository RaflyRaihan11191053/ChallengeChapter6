package com.example.challengechapter6.di

import com.example.challengechapter6.Repository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::Repository)
}