package com.example.challengechapter6.di

import com.example.challengechapter6.viewmodel.DetailItemViewModel
import com.example.challengechapter6.viewmodel.HomeViewModel
import com.example.challengechapter6.viewmodel.UserViewModel
import com.example.challengechapter6.viewmodel.WishlistViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailItemViewModel)
    viewModelOf(::UserViewModel)
    viewModelOf(::WishlistViewModel)

}