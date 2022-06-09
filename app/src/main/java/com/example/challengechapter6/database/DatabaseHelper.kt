package com.example.challengechapter6.database

import com.example.challengechapter5.model.User
import com.example.challengechapter5.model.UserDao
import com.example.challengechapter6.model.Wishlist

class DatabaseHelper(private val userDao: UserDao, private val wishlistDao: WishlistDao) {

    suspend fun login(username: String, password: String) = userDao.login(username, password)
    suspend fun register(user: User) = userDao.register(user)
//    suspend fun getUser(username: String, password: String) = userDao.getUser(username, password)
    suspend fun updateProfile(user: User) = userDao.updateProfile(user)

    suspend fun getWishlist() = wishlistDao.getWishlist()
    suspend fun getWishListById(id: Int) = wishlistDao.getWishListById(id)
    suspend fun addWishList(wishlist: Wishlist) = wishlistDao.addWishlist(wishlist)
    suspend fun deleteWishList(wishlist: Wishlist) = wishlistDao.deleteWishlist(wishlist)

}