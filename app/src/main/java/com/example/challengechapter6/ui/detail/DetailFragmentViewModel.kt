package com.example.challengechapter6.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengechapter5.model.GetAllItemSell
import com.example.challengechapter5.model.User
import com.example.challengechapter6.Repository
import com.example.challengechapter6.model.Wishlist
import kotlinx.coroutines.launch

class DetailFragmentViewModel(private val repository: Repository): ViewModel() {

    private val _detailItem: MutableLiveData<GetAllItemSell> = MutableLiveData()
    val detailItem: LiveData<GetAllItemSell> get() = _detailItem

    private val _isWishlist: MutableLiveData<Wishlist> = MutableLiveData()
    val isWishlist: LiveData<Wishlist> get() = _isWishlist

    fun getDetail(id: Int) {
        viewModelScope.launch {
            _detailItem.postValue(repository.getDetailItems(id))
        }
    }

    fun getWishlist(id: Int) {
        viewModelScope.launch {
            _isWishlist.postValue(repository.getWishListById(id))
        }
    }

    fun addWishlist(wishlist: Wishlist) {
        viewModelScope.launch {
            repository.addWishList(wishlist)
        }
    }

    fun deleteWishlist(wishlist: Wishlist) {
        viewModelScope.launch {
            repository.deleteWishList(wishlist)
        }
    }

}