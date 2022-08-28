package com.example.btcupdates.domain

sealed interface Result<T> {
    class Success<T>(val data: T? = null): Result<T>
    class Error<T>(val data: T? = null, val message: String? = null): Result<T>
}