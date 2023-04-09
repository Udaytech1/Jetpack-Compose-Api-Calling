package com.example.movieapp.data.models


sealed class NetworkResponse<T>(val data: T? = null, val error_message: String? = null) {
    class Loading<T> : NetworkResponse<T>()
    class Success<T>(data: T?) : NetworkResponse<T>(data)
    class Error<T>(data: T?,error_message: String?) : NetworkResponse<T>(data,error_message)
}