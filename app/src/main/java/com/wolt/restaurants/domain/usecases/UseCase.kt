package com.wolt.restaurants.domain.usecases

import com.wolt.restaurants.domain.Result
import com.wolt.restaurants.domain.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

abstract class UseCase<Q : UseCase.Request, R> {

    suspend fun execute(
        request: Q,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Result<R> {
        return safeApiCall(dispatcher) { perform(request) }
    }

    abstract suspend fun perform(request: Q): R

    abstract class Request
}
