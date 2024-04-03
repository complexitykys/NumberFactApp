package com.example.ethernetprac.domain.repository

import com.example.ethernetprac.data.repository.Store
import com.example.ethernetprac.domain.model.NumberData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveNumberFactSpec : Store.ObserveSpec<NumberContext, List<NumberData>> {
    override fun execute(context: NumberContext): Flow<List<NumberData>> {
        return context.numberFactDao.getNumberFacts().map { list ->
            list.map { context.mapper.mapToData(it) }
        }
    }
}