package com.example.challengechapter6.service

import com.example.challengechapter5.service.ApiService

class ApiHelper(private val apiService: ApiService) {

    suspend fun getAllItems() = apiService.getAllItems()
    suspend fun getDetailItems(id: Int) = apiService.getDetailItems(id)

}