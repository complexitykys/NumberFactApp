package com.example.ethernetprac.di

import com.example.ethernetprac.presentation.base.ViewModelNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    fun provideViewModelNavigatorImpl() : ViewModelNavigatorImpl {
        return ViewModelNavigatorImpl()
    }
}