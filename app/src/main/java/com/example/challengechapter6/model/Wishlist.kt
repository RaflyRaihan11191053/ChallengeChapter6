package com.example.challengechapter6.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Wishlist (
    @PrimaryKey(autoGenerate = true) var id: Int,
    val image: String,
    val title: String,
    val category: String,
    val price: Double
): Parcelable