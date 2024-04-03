package com.example.ethernetprac.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ethernetprac.data.database.dao.NumberFactDao
import com.example.ethernetprac.data.database.entity.NumberFactEntity

@Database(
    entities = [NumberFactEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val numberFactDao: NumberFactDao
}