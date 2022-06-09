package com.example.challengechapter6

import com.example.challengechapter5.model.User
import com.example.challengechapter6.database.DatabaseHelper
import com.example.challengechapter6.model.Wishlist
import com.example.challengechapter6.service.ApiHelper
import kotlinx.coroutines.flow.Flow
import com.example.challengechapter6.datastore.UserManager

class Repository(private val apiHelper: ApiHelper, private val databaseHelper: DatabaseHelper, private val userManager: UserManager) {

    suspend fun getAllItems() = apiHelper.getAllItems()
    suspend fun getDetailItems(id: Int) = apiHelper.getDetailItems(id)

    suspend fun getWishList() = databaseHelper.getWishlist()
    suspend fun getWishListById(id: Int) = databaseHelper.getWishListById(id)
    suspend fun addWishList(wishlist: Wishlist) = databaseHelper.addWishList(wishlist)
    suspend fun deleteWishList(wishlist: Wishlist) = databaseHelper.deleteWishList(wishlist)


    suspend fun setUser(user: User) = userManager.setUser(user)
    suspend fun getUserFromPref(): Flow<User> = userManager.getUser()
    suspend fun deleteUser() = userManager.deleteUserFromPref()

    suspend fun login(username: String, password: String) = databaseHelper.login(username, password)
    suspend fun register(user: User) = databaseHelper.register(user)
    suspend fun updateProfile(user: User) = databaseHelper.updateProfile(user)

}