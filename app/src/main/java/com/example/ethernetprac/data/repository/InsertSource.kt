package com.example.ethernetprac.data.repository

import com.example.ethernetprac.data.repository.spec.Specification

interface InsertSource<Context> {
    suspend fun<T> insert(spec: Specification.InsertSpec<Context, T>) : T
}