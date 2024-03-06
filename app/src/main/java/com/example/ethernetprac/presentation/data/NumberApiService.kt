package com.example.ethernetprac.presentation.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class NumberApiService {
    private val client = OkHttpClient()

    suspend fun fetchNumberFact(number: Long) : String = withContext(Dispatchers.IO) {
        val url = "http://numbersapi.com/$number"
        val request = Request.Builder()
            .url(url)
            .build()

        try {
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            response.body?.string() ?: "Empty response body"
        } catch (e: IOException) {
            e.printStackTrace()
            "Error fetching data: ${e.message}"
        }
    }
}