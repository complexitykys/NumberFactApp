package com.example.ethernetprac.di

import android.content.Context
import androidx.room.Room
import com.example.ethernetprac.data.database.AppDatabase
import com.example.ethernetprac.data.database.dao.NumberFactDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val FILE_NAME = "number_db"


    @Provides
    @Singleton
    fun provideRoomDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java,FILE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideNumberFactDao(appDatabase: AppDatabase) : NumberFactDao {
        return appDatabase.numberFactDao
    }

}