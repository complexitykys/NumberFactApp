package com.example.ethernetprac.data.network

interface NumberApiService {

    suspend fun getNumberFact(number: String) : String

    suspend fun getRandomFact() : String
}