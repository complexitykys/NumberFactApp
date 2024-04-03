package com.example.ethernetprac.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "number_fact")
data class NumberFactEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(index = true, name = "number")
    val number: String,
    @ColumnInfo(name = "fact")
    val fact: String
)
