package com.example.ethernetprac.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ethernetprac.data.database.entity.NumberFactEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface NumberFactDao {

    @Query("SELECT * FROM number_fact WHERE number = :number AND fact = :fact LIMIT 1")
    suspend fun getNumber(number: String, fact: String) : NumberFactEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNumberFact(numberFactEntity: NumberFactEntity) : Long

    @Query("SELECT EXISTS(SELECT * FROM number_fact WHERE number = :number AND fact = :fact)")
    suspend fun isNumberFactExist(number: String, fact: String) : Boolean

    @Query("SELECT * FROM number_fact WHERE number = :number")
    fun getNumbersFact(number: String) : Flow<List<NumberFactEntity>>

    @Query("SELECT * FROM number_fact")
    fun getNumberFacts() : Flow<List<NumberFactEntity>>


}