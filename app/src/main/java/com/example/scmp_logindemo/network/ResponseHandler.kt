package com.example.scmp_logindemo.network

import retrofit2.HttpException
import java.net.SocketTimeoutException

/**
 * Created by Archer on 2022/6/12
 */
class ResponseHandler {
    fun <T : Any> handleSuccess(data: T?): Response<T> {
        return Response.success(data)
    }

    fun <T : Any> handleError(e: Exception): Response<T> {
        return when (e) {
            is HttpException -> {
                Response.error("Error Code: ${e.code()}", null)
            }
            is SocketTimeoutException -> {
                Response.error("Socket Timeout", null)
            }
            else -> {
                Response.error("Something Wrong, Please try later.", null)
            }
        }
    }
}