package com.example.challengechapter6.viewmodel

import androidx.lifecycle.*
import com.example.challengechapter5.model.UserDatabase
import com.example.challengechapter6.datastore.UserManager
import com.example.challengechapter6.model.Wishlist
import kotlinx.coroutines.launch

class WishlistViewModel(private val myDb: UserDatabase): ViewModel() {

    private val _allWishlist: MutableLiveData<List<Wishlist>> = MutableLiveData()

    val allWishlist: LiveData<List<Wishlist>> = _allWishlist

    fun getAllWishlist() {
        viewModelScope.launch {
            val allWishlist = myDb.wishlistDao().getWishlist()
            _allWishlist.postValue(allWishlist)
        }
    }

}

class WishlistViewModelFactory(private val myDb: UserDatabase) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WishlistViewModel::class.java)){
            return WishlistViewModel(myDb) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: "+modelClass.name)
    }

}