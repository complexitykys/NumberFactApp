package com.example.ethernetprac.domain.repository

import com.example.ethernetprac.data.repository.spec.Specification
import com.example.ethernetprac.domain.model.NumberData


/**
 * Features. Can provide in code.
 */
class InsertNumberFactSpec(private val numberFact: NumberData) :
    Specification.InsertSpec<NumberContext, NumberData> {
    override suspend fun execute(context: NumberContext): NumberData {
        val existedNumber = context.numberFactDao.getNumber(numberFact.number, numberFact.fact)
        if (existedNumber != null) {
            return context.mapper.mapToData(existedNumber)
        }
        context.numberFactDao.saveNumberFact(context.mapper.mapToEntity(numberFact, 0))
        return numberFact
    }
}