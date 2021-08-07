package com.example.petadopt.data.network.animals

import com.example.petadopt.data.network.animals.response.AnimalsNetworkEntity
import okhttp3.OkHttpClient
import okhttp3.internal.connection.ConnectInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AnimalsApiService {
    @GET("J2OS")
    suspend fun getAnimals(): AnimalsNetworkEntity

    /**
     * Make connection with the API
     * TODO: Add connectivityInterceptor (look at WemaMonitor OpenWeatherApiService)
     */
    companion object {
        operator fun invoke(): AnimalsApiService {
            val okHttpClient = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://jsonkeeper.com/b/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AnimalsApiService::class.java)
        }
    }
}