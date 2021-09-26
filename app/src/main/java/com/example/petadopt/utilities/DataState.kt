package com.example.petadopt.utilities

import java.lang.Exception

// TODO: What is a sealed class?
// TODO: Checkout Mitch's full DataState class
sealed class DataState<out R> {
    data class Success<out T>(val data: T): DataState<T>()
    data class Error(val exception: Exception): DataState<Nothing>()
    object Loading: DataState<Nothing>()
}