package com.example.ethernetprac.domain.repository

import com.example.ethernetprac.data.database.dao.NumberFactDao
import com.example.ethernetprac.data.network.NumberApiService
import com.example.ethernetprac.data.repository.InsertSource
import com.example.ethernetprac.data.repository.RetrieveSource
import com.example.ethernetprac.data.repository.Store
import com.example.ethernetprac.data.repository.spec.Specification
import com.example.ethernetprac.domain.NumberMapper
import kotlinx.coroutines.flow.Flow

class NumberContext(
    val numberFactDao: NumberFactDao,
    val numberApiService: NumberApiService,
    val mapper: NumberMapper
)

class NumberRepository(private val numberContext: NumberContext) : InsertSource<NumberContext>,
    RetrieveSource<NumberContext>, Store<NumberContext> {
    override suspend fun <T> insert(spec: Specification.InsertSpec<NumberContext, T>): T {
        return spec.execute(numberContext)
    }

    override suspend fun <T> retrieve(spec: Specification.RetrieveSpec<NumberContext, T>): T {
        return spec.execute(numberContext)
    }

    override fun <T> observe(spec: Store.ObserveSpec<NumberContext, T>): Flow<T> {
        return spec.execute(numberContext)
    }
}