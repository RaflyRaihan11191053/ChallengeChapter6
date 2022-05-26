package com.example.challengechapter6.viewmodel

import androidx.lifecycle.*
import com.example.challengechapter5.model.User
import com.example.challengechapter6.datastore.UserManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(private val pref: UserManager): ViewModel() {

    private val _userSession: MutableLiveData<User> = MutableLiveData()
    val userSession: LiveData<User> get() = _userSession

    suspend fun setDataUser(user: User) {
        pref.setUser(user)
    }

    fun getDataUser() {
        viewModelScope.launch {
            pref.getUser().collect{
                _userSession.postValue(it)
            }
        }
    }

}