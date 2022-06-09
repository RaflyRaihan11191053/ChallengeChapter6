package com.example.challengechapter5.service

import com.example.challengechapter5.model.GetAllItemSell
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("products")
    suspend fun getAllItems(): List<GetAllItemSell>

    @GET("products/{id}")
    suspend fun getDetailItems(@Path ("id") id: Int): GetAllItemSell

//    @POST("products")
//    fun postItem(@Body request: PostAnItemRequest): Call<PostAnItemResponse>

}
