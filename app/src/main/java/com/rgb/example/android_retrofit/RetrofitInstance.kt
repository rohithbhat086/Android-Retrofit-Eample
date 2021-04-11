package com.rgb.example.android_retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {

    private fun RetrofitInstance(){}

    companion object {
        private val BASE_URL = "https://jsonplaceholder.typicode.com"

        private val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }
        private val client = OkHttpClient.Builder().apply{
            addInterceptor(interceptor)
            connectTimeout(30, TimeUnit.SECONDS)  // Sample values. Set these based on network state and device capability
            readTimeout(20, TimeUnit.SECONDS)
            writeTimeout(25, TimeUnit.SECONDS)
        }.build()


        private var INSTANCE: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

        fun getInstance(): Retrofit {
            return INSTANCE
        }
    }
}