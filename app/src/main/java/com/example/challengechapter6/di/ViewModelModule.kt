package com.example.challengechapter6.di

import com.example.challengechapter6.ui.detail.DetailFragmentViewModel
import com.example.challengechapter6.ui.home.HomeFragmentViewModel
import com.example.challengechapter6.ui.login.LoginFragmentViewModel
import com.example.challengechapter6.ui.profile.ProfileFragmentViewModel
import com.example.challengechapter6.ui.register.RegisterFragmentViewModel
import com.example.challengechapter6.ui.splash.SplashFragmentViewModel
import com.example.challengechapter6.ui.wishlist.WishlistFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::HomeFragmentViewModel)
    viewModelOf(::LoginFragmentViewModel)
    viewModelOf(::RegisterFragmentViewModel)
    viewModelOf(::SplashFragmentViewModel)
    viewModelOf(::DetailFragmentViewModel)
    viewModelOf(::WishlistFragmentViewModel)
    viewModelOf(::ProfileFragmentViewModel)

}