package com.example.ethernetprac.di

import android.util.Log
import com.example.ethernetprac.data.network.NumberApiService
import com.example.ethernetprac.data.network.NumberApiServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.utils.io.writer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(okHttpClient: OkHttpClient): HttpClient {
        val client = HttpClient(OkHttp) {
            engine {
                preconfigured = okHttpClient
            }
            install(Logging) {
                logger = object: Logger{
                    override fun log(message: String) {
                        Log.e("HTTP", "Message $message")
                    }
                }
                level = LogLevel.ALL
            }
        }
        return client
    }

    @Provides
    @Singleton
    fun provideNumberApi(httpClient: HttpClient): NumberApiService {
        return NumberApiServiceImpl(httpClient)
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
    }

}