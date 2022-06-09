package com.example.challengechapter6.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengechapter5.model.User
import com.example.challengechapter6.Repository
import kotlinx.coroutines.launch

class RegisterFragmentViewModel(private val repository: Repository): ViewModel() {

    private val _register: MutableLiveData<Long> = MutableLiveData()
    val register: LiveData<Long> get() = _register

    fun register(user: User) {
        viewModelScope.launch {
            _register.postValue(repository.register(user))
        }
    }

}