package com.example.scmp_logindemo.network

import com.example.scmp_logindemo.model.LoginResponse
import com.example.scmp_logindemo.model.UserInfo
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Archer on 2022/6/12
 */
interface ApiService {
    @POST("api/login")
    suspend fun login(@Body userInfo: UserInfo): LoginResponse
}