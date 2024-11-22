package com.frenkel.data.models

sealed class RequestResult<out E : Any>(open val data: E? = null) {
    class InProgress<E : Any>(
        data: E? = null
    ) : RequestResult<E>(data)

    class Success<E : Any>(
        override val data: E
    ) : RequestResult<E>(data)

    class Error<E : Any>(
        data: E? = null,
        val error: Throwable? = null
    ) : RequestResult<E>(data)
}