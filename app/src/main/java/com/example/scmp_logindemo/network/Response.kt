package com.example.scmp_logindemo.network

/**
 * Created on 2021/3/9.
 */

data class Response<out T>(val state: State, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Response<T> = Response(State.SUCCESS, data, message = null)

        fun <T> error(message: String, data: T?): Response<T> = Response(State.ERROR, data, message)

        fun <T> loading(data: T?): Response<T> = Response(State.LOADING, data, message = null)

    }
}