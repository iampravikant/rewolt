package com.wolt.restaurants.domain

import com.wolt.restaurants.domain.entities.ErrorResponse

sealed class Result<out T> {
    data class Success<out T>(val value: T): Result<T>()
    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null): Result<Nothing>()
    object NetworkError: Result<Nothing>()
}
