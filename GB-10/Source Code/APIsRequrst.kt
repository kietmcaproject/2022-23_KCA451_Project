package com.app.pepens.utils

import com.app.pepens.model.Login.Login
import com.app.pepens.model.Login.LoginData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Vikas Kumar Singh on 29/06/20.
 */

interface APIsRequrst {

    @Multipart
    @POST("user/AddUpdateUser")
    fun updateUserWithImage(@Part defaultPicture: MultipartBody.Part?, @Part("User") business: RequestBody?): Call<Login>

    @Multipart
    @POST("user/AddUpdateUser")
    fun updateUser(@Part("User") business: RequestBody?): Call<Login>

    @GET("user/GetUser/")
    fun getUser(@Query("userId") userId: String, @Query("token") token: String): Call<LoginData>

    @POST("user/AppLoginWithMobileNo/")
    fun loginWithMobile(@Body loginData: LoginData): Call<Login>
//    fun loginWithMobile(@Body("CountryCode") countryCode: String, @Query("MobileNo") mobileNo: String, @Query("AuthenticationUsing") authenticationUsing: String, @Query("AuthenticationToken") authenticationToken: String): Call<Login>

}