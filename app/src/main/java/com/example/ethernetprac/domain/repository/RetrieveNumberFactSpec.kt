package com.example.ethernetprac.domain.repository

import com.example.ethernetprac.data.database.entity.NumberFactEntity
import com.example.ethernetprac.data.repository.spec.Specification
import com.example.ethernetprac.domain.model.NumberData

class RetrieveNumberFactSpec(private val number: String) : Specification.RetrieveSpec<NumberContext, NumberData> {
    override suspend fun execute(context: NumberContext): NumberData {
        val fact = context.numberApiService.getNumberFact(number)
        val numberFact = context.numberFactDao.getNumber(number, fact)
        return if (numberFact == null) {
            context.numberFactDao.saveNumberFact(NumberFactEntity(0, number, fact))
            context.mapper.mapToData(number, fact)
        } else {
            context.mapper.mapToData(numberFact)
        }
    }
}