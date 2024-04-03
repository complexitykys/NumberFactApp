package com.example.ethernetprac.di

import com.example.ethernetprac.data.database.dao.NumberFactDao
import com.example.ethernetprac.data.network.NumberApiService
import com.example.ethernetprac.domain.NumberMapper
import com.example.ethernetprac.domain.repository.NumberContext
import com.example.ethernetprac.domain.repository.NumberRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNumberRepository(dao: NumberFactDao, service: NumberApiService): NumberRepository {
        return NumberRepository(NumberContext(dao, service, NumberMapper()))
    }
}