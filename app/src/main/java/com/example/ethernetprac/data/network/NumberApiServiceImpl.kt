package com.example.ethernetprac.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.utils.EmptyContent.headers

class NumberApiServiceImpl(private val httpClient: HttpClient): NumberApiService {

    companion object {
        private const val BASE_URL = "http://numbersapi.com/"
    }

    override suspend fun getNumberFact(number: String): String {
        val response: HttpResponse = httpClient.post("${BASE_URL}$number")
        return response.bodyAsText()
    }

    override suspend fun getRandomFact(): String {
        return httpClient.get("${BASE_URL}random/math").bodyAsText()
    }
}