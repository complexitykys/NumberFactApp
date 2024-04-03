package com.example.ethernetprac.data.repository.spec

sealed interface Specification<Context, Output> {

    suspend fun execute(context: Context) : Output
    interface InsertSpec<Context, Output> : Specification<Context, Output>
    interface RetrieveSpec<Context, Output> : Specification<Context, Output>
}