package com.example.timitonicapp.view.service

import com.example.timitonicapp.view.response.BooksDataResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

//build the apiservise for the list of books of BooksDataResponse // go to BooksDataResponse
///androiddeveloper/androiddeveloper/gT2P-LIhA-xiNT-bxzF-HJg4-UUb1-Kcyn
interface ApiService {

    @FormUrlEncoded
    @POST("live/api.php")
    suspend fun getAllBooks(
        @Field("version") version: String,
        @Field("req") req: String,
        @Field("u_c") u_c: String,
        @Field("o_u") o_u: String,
        @Field("sesskey") sesskey: String
    ): BooksDataResponse


}