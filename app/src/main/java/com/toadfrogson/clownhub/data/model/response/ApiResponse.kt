package com.toadfrogson.clownhub.data.model.response

import java.lang.Exception

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T): ApiResponse<T>()
    data class Failure(val errorMessage: String, val exception: Exception?): ApiResponse<Nothing>()
}
