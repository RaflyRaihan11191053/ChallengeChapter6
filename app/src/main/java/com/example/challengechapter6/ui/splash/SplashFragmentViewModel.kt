package com.example.challengechapter6.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengechapter5.model.User
import com.example.challengechapter6.Repository
import kotlinx.coroutines.launch

class SplashFragmentViewModel(private val repository: Repository): ViewModel() {

    private val _userSession: MutableLiveData<User> = MutableLiveData()
    val userSession: LiveData<User> get() = _userSession

    fun getDataUser() {
        viewModelScope.launch {
            repository.getUserFromPref().collect{
                _userSession.postValue(it)
            }
        }
    }

}