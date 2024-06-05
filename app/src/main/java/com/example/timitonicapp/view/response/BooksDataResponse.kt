package com.example.timitonicapp.view.response

import com.google.gson.annotations.SerializedName

//this is the data of books // the JSON begin with status
//BokksDataresponse toma el status y allBooks String
data class BooksDataResponse(
    @SerializedName("status") val status: String,
    @SerializedName("allBooks") val allBooks: AllBooks
)

//Allbooks toma el numero de libros y entra al array de libros books
data class AllBooks(
    @SerializedName("nbBooks") val nbBooks: Int,
    @SerializedName("books") val books: List<Books>
)

//dentro de la lista de Book entra a ownerPrefs que es un String
data class Books(
    @SerializedName("ownerPrefs") val ownerPrefs: OwnerPrefs
)

//y dentro de PwnerPrefs toma los valores de oCoverType que es la imagen y title que es el nombre del libro
data class OwnerPrefs(
    @SerializedName("oCoverImage") val oCoverImage: String,
    @SerializedName("title") val title: String
)

