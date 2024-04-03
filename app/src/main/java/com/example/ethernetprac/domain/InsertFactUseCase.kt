package com.example.ethernetprac.domain

import com.example.ethernetprac.data.database.dao.NumberFactDao
import com.example.ethernetprac.data.database.entity.NumberFactEntity
import com.example.ethernetprac.domain.main.AppNumber
import com.example.ethernetprac.domain.main.Fact
import javax.inject.Inject

/**
 * Features. Can provide in code.
 */
class InsertFactUseCase @Inject constructor(private val numberFactDao: NumberFactDao) :
    UseCase<Pair<AppNumber, Fact>, Boolean> {
    override suspend fun execute(input: Input<Pair<AppNumber, Fact>>): Boolean {
        val (number, fact) = (input as Input.Data<Pair<AppNumber, Fact>>).data
        return if (numberFactDao.isNumberFactExist(number.value, fact.value)) {
            false
        } else {
            numberFactDao.saveNumberFact(NumberFactEntity(number = number.value, fact = fact.value))
            true
        }
    }
}