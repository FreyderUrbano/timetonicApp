package com.example.timitonicapp.view.service

import com.example.timitonicapp.view.response.AppkeyResponse
import com.example.timitonicapp.view.response.PauthkeyResponse
import com.example.timitonicapp.view.response.SesskeyResponse
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthenticationService {

    @FormUrlEncoded
    @POST("createAppkey")
    suspend fun createApikey(
        @Field("email") email: TextInputEditText,
        @Field("pass") password: TextInputEditText
    ): Response<AppkeyResponse>

    @FormUrlEncoded
    @POST("createPauthkey")
    suspend fun createPauthkey(@Field("appkey") appkey: AppkeyResponse?): Response<PauthkeyResponse>

    @FormUrlEncoded
    @POST("createSesskey")
    suspend fun createSesskey(@Field("pauthkey") pauthkey: PauthkeyResponse?): Response<SesskeyResponse>

}
