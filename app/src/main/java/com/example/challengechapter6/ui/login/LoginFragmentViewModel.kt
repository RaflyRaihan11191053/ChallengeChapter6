package com.example.challengechapter6.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengechapter5.model.User
import com.example.challengechapter6.Repository
import kotlinx.coroutines.launch

class LoginFragmentViewModel(private val repository: Repository): ViewModel() {

    private val _userSession: MutableLiveData<User> = MutableLiveData()
    val userSession: LiveData<User> get() = _userSession

    fun setDataUser(user: User) {
        viewModelScope.launch {
            repository.setUser(user)
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _userSession.postValue(repository.login(username, password))
        }
    }

}