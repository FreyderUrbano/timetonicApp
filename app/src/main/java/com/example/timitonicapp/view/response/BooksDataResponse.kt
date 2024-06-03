package com.example.timitonicapp.view.response

import com.google.gson.annotations.SerializedName

data class BooksDataResponse(
    val id: String,
    val title: String,
    val author: String
)
