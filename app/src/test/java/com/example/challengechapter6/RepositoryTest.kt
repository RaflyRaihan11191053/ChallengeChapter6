package com.example.challengechapter6

import com.example.challengechapter5.model.GetAllItemSell
import com.example.challengechapter5.model.User
import com.example.challengechapter5.service.ApiService
import com.example.challengechapter6.database.DatabaseHelper
import com.example.challengechapter6.datastore.UserManager
import com.example.challengechapter6.model.Wishlist
import com.example.challengechapter6.service.ApiHelper
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class RepositoryTest {

    private val user = User(null, "Rafly", "rafly@gmail.com", "123", "image")
    private val wishlist = Wishlist(1,"image", "kaos hitam", "mens clothes", 1.2)

    private lateinit var apiService: ApiService
    private lateinit var apiHelper: ApiHelper
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var userManager: UserManager

    private lateinit var repository: Repository

    @Before
    fun setUp() {
        apiService = mockk()
        databaseHelper = mockk()
        userManager = mockk()
        apiHelper = ApiHelper(apiService)
        repository = Repository(apiHelper, databaseHelper, userManager)
    }

    @Test
    fun getAllItems(): Unit = runBlocking {
        val responseGetAllItems = mockk<List<GetAllItemSell>>()
        every {
            runBlocking {
                apiService.getAllItems()
            }
        } returns responseGetAllItems

        repository.getAllItems()

        verify {
            runBlocking {
                apiService.getAllItems()
            }
        }
    }

    @Test
    fun getDetailItems(): Unit = runBlocking {
        val responseGetDetailItems = mockk<GetAllItemSell>()
        every {
            runBlocking {
                apiService.getDetailItems(1)
            }
        } returns responseGetDetailItems

        repository.getDetailItems(1)

        verify {
            runBlocking {
                apiService.getDetailItems(1)
            }
        }
    }
//
//    @Test
//    fun getWishList() {
//    }
//
//    @Test
//    fun getWishListById() {
//    }
//
//    @Test
//    fun addWishList() {
//    }
//
//    @Test
//    fun deleteWishList() {
//    }
//
//    @Test
//    fun setUser() {
//    }
//
//    @Test
//    fun getUserFromPref() {
//    }
//
//    @Test
//    fun deleteUser() {
//    }
//
//    @Test
//    fun login() {
//    }
//
//    @Test
//    fun register() {
//    }
//
//    @Test
//    fun updateProfile() {
//    }
}