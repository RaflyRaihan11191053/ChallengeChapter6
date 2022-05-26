package com.example.challengechapter6.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengechapter5.model.GetAllItemSell
import com.example.challengechapter5.model.User
import com.example.challengechapter6.Repository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentViewModel(private val repository: Repository): ViewModel() {

//    val item: MutableLiveData<List<GetAllItemSell>> = MutableLiveData()
//    val id = item

    private val _userSession: MutableLiveData<User> = MutableLiveData()
    val userSession: LiveData<User> get() = _userSession

    private val _home: MutableLiveData<List<GetAllItemSell>> = MutableLiveData()
    val home: LiveData<List<GetAllItemSell>> get() = _home

    fun getAllItem() {
        viewModelScope.launch {
            _home.postValue(repository.getAllItems())
        }
    }

//    val user: MutableLiveData<User> = MutableLiveData()

    fun getDataUser() {
        viewModelScope.launch {
            repository.getUserFromPref().collect {
                _userSession.postValue(it)
            }
        }
    }

}