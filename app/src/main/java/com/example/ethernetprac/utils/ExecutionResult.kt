package com.example.ethernetprac.utils

sealed interface ExecutionResult<out T, out R>

data class Success<T: Any>(
    val result: T
) : ExecutionResult<T, Nothing>

data class Failure<T: Any>(
    val error: T
) : ExecutionResult<Nothing, T>