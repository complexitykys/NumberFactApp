package com.example.ethernetprac.domain.repository

import com.example.ethernetprac.data.database.entity.NumberFactEntity
import com.example.ethernetprac.data.repository.spec.Specification
import com.example.ethernetprac.domain.model.NumberData

class GetRandomNumberSpec : Specification.RetrieveSpec<NumberContext, NumberData> {
    override suspend fun execute(context: NumberContext): NumberData {
        val response = context.numberApiService.getRandomFact()
        val number = response.split(" ").first()
        val numberFact = context.numberFactDao.getNumber(number, response)
        return if (numberFact == null) {
            context.numberFactDao.saveNumberFact(NumberFactEntity(0, number, response))
            context.mapper.mapToData(number, response)
        } else {
            context.mapper.mapToData(numberFact)
        }
    }
}