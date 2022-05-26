package com.example.challengechapter6.database

import androidx.room.*
import com.example.challengechapter5.model.User
import com.example.challengechapter6.model.Wishlist

@Dao
interface WishlistDao {

    @Query("SELECT * FROM Wishlist")
    fun getWishlist(): List<Wishlist>

    @Query("SELECT * FROM Wishlist WHERE id = :id")
    fun getWishListById(id: Int): Wishlist?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWishlist(wishlist: Wishlist): Long?

    @Delete
    fun deleteWishlist(wishlist: Wishlist): Int?

}