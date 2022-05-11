package com.example.challengechapter5.service

import com.example.challengechapter5.model.GetAllItemSell
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("products")
    fun getAllItems(): Call<List<GetAllItemSell>>

    @GET("products/{id}")
    fun getDetailItems(@Path ("id") id: Int): Call<GetAllItemSell>

//    @POST("products")
//    fun postItem(@Body request: PostAnItemRequest): Call<PostAnItemResponse>

}
