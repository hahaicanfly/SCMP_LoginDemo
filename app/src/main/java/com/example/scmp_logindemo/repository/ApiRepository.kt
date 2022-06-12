package com.example.scmp_logindemo.repository

import com.example.scmp_logindemo.model.LoginResponse
import com.example.scmp_logindemo.model.UserInfo
import com.example.scmp_logindemo.network.ApiService
import com.example.scmp_logindemo.network.Response
import com.example.scmp_logindemo.network.ResponseHandler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Archer on 2022/6/12
 */
class ApiRepository {

    companion object {
        const val TIMEOUT_SEC = 15L
    }

    private val apiService: ApiService
    private val responseHandler: ResponseHandler

    init {
        apiService = provideRetrofit(provideOkHttpClient()).create(ApiService::class.java)
        responseHandler = ResponseHandler()
    }


    suspend fun doLogin(email: String, pwd: String): Response<LoginResponse> {
        return try {
            val resp = apiService.login(UserInfo(email, pwd))
            responseHandler.handleSuccess(resp)
        } catch (e: Exception) {
            responseHandler.handleError(e)
        }
    }

    private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://reqres.in/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
        .callTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
}