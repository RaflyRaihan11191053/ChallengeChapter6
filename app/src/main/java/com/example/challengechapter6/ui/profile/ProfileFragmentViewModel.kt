package com.example.challengechapter6.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengechapter5.model.GetAllItemSell
import com.example.challengechapter5.model.User
import com.example.challengechapter6.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileFragmentViewModel(private val repository: Repository): ViewModel() {

    private val _editProfile: MutableLiveData<Int> = MutableLiveData()
    val editProfile: LiveData<Int> get() = _editProfile

    fun editUser(user: User) {
        viewModelScope.launch {
            _editProfile.postValue(repository.updateProfile(user))
        }
    }

    fun setUser(user: User) {
        viewModelScope.launch {
            repository.setUser(user)
        }
    }

    fun deleteUserFromPref() {
        viewModelScope.launch {
            repository.deleteUser()
        }
    }

}