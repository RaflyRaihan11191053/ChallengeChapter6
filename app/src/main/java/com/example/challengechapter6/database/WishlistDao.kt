package com.example.challengechapter6.database

import androidx.room.*
import com.example.challengechapter5.model.User
import com.example.challengechapter6.model.Wishlist

@Dao
interface WishlistDao {

    @Query("SELECT * FROM Wishlist")
    suspend fun getWishlist(): List<Wishlist>

    @Query("SELECT * FROM Wishlist WHERE id = :id")
    suspend fun getWishListById(id: Int): Wishlist?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWishlist(wishlist: Wishlist): Long?

    @Delete
    suspend fun deleteWishlist(wishlist: Wishlist): Int?

}