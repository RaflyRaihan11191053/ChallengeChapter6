package com.example.challengechapter6.database

import com.example.challengechapter5.model.User
import com.example.challengechapter5.model.UserDao
import com.example.challengechapter6.model.Wishlist

class DatabaseHelper(private val userDao: UserDao, private val wishlistDao: WishlistDao) {

    fun login(username: String, password: String) = userDao.login(username, password)
    fun register(user: User) = userDao.register(user)
    fun getUser(username: String, password: String) = userDao.getUser(username, password)
    fun updateProfile(user: User) = userDao.updateProfile(user)

    fun getWishlist() = wishlistDao.getWishlist()
    fun getWishListById(id: Int) = wishlistDao.getWishListById(id)
    fun addWishList(wishlist: Wishlist) = wishlistDao.addWishlist(wishlist)
    fun deleteWishList(wishlist: Wishlist) = wishlistDao.deleteWishlist(wishlist)

}