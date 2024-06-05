package com.example.timitonicapp.view.service

import com.example.timitonicapp.view.response.AppkeyResponse
import com.example.timitonicapp.view.response.PauthkeyResponse
import com.example.timitonicapp.view.response.SesskeyResponse
import com.google.android.material.textfield.TextInputEditText
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthenticationService {

    @FormUrlEncoded
    @POST("live/api.php")
    suspend fun createAppKey(
        @Field("version") version: String,
        @Field("req") req: String,
        @Field("appname") appname: String
    ): ResponseBody

    @FormUrlEncoded
    @POST("live/api.php")
    suspend fun createOAuthKey(
        @Field("version") version: String,
        @Field("req") req: String,
        @Field("login") login: String,
        @Field("pwd") pwd: String,
        @Field("appkey") appkey: String
    ): ResponseBody

    @FormUrlEncoded
    @POST("live/api.php")
    suspend fun createSesskey(
        @Field("version") version: String,
        @Field("req") req: String,
        @Field("o_u") o_u: String,
        @Field("u_c") o_c: String,
        @Field("oauthkey") oauthkey: String
    ): ResponseBody

}
