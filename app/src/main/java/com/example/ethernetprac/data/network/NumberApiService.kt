package com.example.ethernetprac.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface NumberApiService {
    @GET("{number}")
    suspend fun getNumberFact(@Path("number") number: String) : String

    @GET("random/math")
    suspend fun getRandomFact() : String
}