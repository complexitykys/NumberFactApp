package com.example.ethernetprac.data.repository

import com.example.ethernetprac.data.repository.spec.Specification

interface RetrieveSource<Context> {
    suspend fun<T> retrieve(spec: Specification.RetrieveSpec<Context, T>) : T
}