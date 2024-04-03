package com.example.ethernetprac.data.repository

import kotlinx.coroutines.flow.Flow

interface Store<Context> {
    interface ObserveSpec<Context, Output> {
        fun execute(context: Context) : Flow<Output>
    }
    fun<T> observe(spec: ObserveSpec<Context, T>) : Flow<T>
}