package com.example.challengechapter6.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challengechapter5.model.GetAllItemSell
import com.example.challengechapter5.model.UserDatabase
import com.example.challengechapter5.service.ApiClient
import com.example.challengechapter6.model.Wishlist
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailItemViewModel(private val myDb: UserDatabase): ViewModel() {

    val detail: MutableLiveData<GetAllItemSell> = MutableLiveData()

    fun getDetail(id: Int) {
        ApiClient.instance.getDetailItems(id)
            .enqueue(object : Callback<GetAllItemSell> {
                override fun onResponse(
                    call: Call<GetAllItemSell>,
                    response: Response<GetAllItemSell>
                ) {
                    response.body()?.let { data ->
                        detail.postValue(data)
                    }
                }
                override fun onFailure(call: Call<GetAllItemSell>, t: Throwable) {
                    ApiClient.instance.getDetailItems(id).cancel()
                }
            })
    }

    private val _isWishlist = MutableLiveData<Boolean>()
    val isWishlist = _isWishlist

    fun changeWishlist(state: Boolean){
        _isWishlist.postValue(state)
    }
    fun getWishlist(id:Int) = myDb.wishlistDao().getWishListById(id)
    fun addWishlist(wishlist: Wishlist) = myDb.wishlistDao().addWishlist(wishlist)
    fun deleteWishlist(wishlist: Wishlist) = myDb.wishlistDao().deleteWishlist(wishlist)

}

class DetailViewModelFactory(private val myDb: UserDatabase): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailItemViewModel::class.java)){
            return DetailItemViewModel(myDb) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}