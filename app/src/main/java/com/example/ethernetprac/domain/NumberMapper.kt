package com.example.ethernetprac.domain

import com.example.ethernetprac.data.database.entity.NumberFactEntity
import com.example.ethernetprac.domain.model.NumberData


class NumberMapper {
    fun mapToData(entity: NumberFactEntity): NumberData {
        return NumberData(entity.number, entity.fact)
    }

    fun mapToEntity(entity: NumberData, id: Int = 0): NumberFactEntity {
        return NumberFactEntity(id, entity.number, entity.fact)
    }

    fun mapToData(number: String, fact: String) : NumberData {
        return NumberData(number, fact)
    }
}