package com.example.ethernetprac.domain

interface UseCase<I, O> {
    suspend fun execute(input: Input<I> = Input.EMPTY): O
}
sealed interface Input<out O> {
    data class Data<T : Any>(
        val data: T
    ) : Input<T>

    data object EMPTY : Input<Nothing>
}