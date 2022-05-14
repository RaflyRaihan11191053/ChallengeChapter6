package com.example.challengechapter6.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.challengechapter5.model.User
import com.example.challengechapter6.datastore.UserManager

class UserViewModel(private val pref: UserManager): ViewModel() {

    suspend fun setDataUser(user: User) {
        pref.setUser(user)
    }

    fun getDataUser(): LiveData<User> {
        return pref.getUser().asLiveData()
    }

}