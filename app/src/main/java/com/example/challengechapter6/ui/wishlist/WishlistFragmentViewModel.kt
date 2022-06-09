package com.example.challengechapter6.ui.wishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengechapter6.Repository
import com.example.challengechapter6.model.Wishlist
import kotlinx.coroutines.launch

class WishlistFragmentViewModel(private val repository: Repository): ViewModel() {

    private val _allWishlist: MutableLiveData<List<Wishlist>> = MutableLiveData()
    val allWishlist: LiveData<List<Wishlist>> get() = _allWishlist

    fun getWishlist() {
        viewModelScope.launch {
            _allWishlist.postValue(repository.getWishList())
        }
    }

}