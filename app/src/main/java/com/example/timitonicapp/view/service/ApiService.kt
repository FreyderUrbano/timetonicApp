package com.example.timitonicapp.view.service

import com.example.timitonicapp.view.response.BooksDataResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("getAllBooks")
    suspend fun getAllBooks(): List<BooksDataResponse>

}