package com.example.petadopt.data.network.animals

import com.example.petadopt.data.network.animals.response.DogNetworkEntity
import okhttp3.OkHttpClient
import okhttp3.internal.connection.ConnectInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface AnimalApiService {
    @Headers("secret-key: \$2b\$10\$2EibYEqbBLQHlgtLFTVXRem0/GhDxzFpE3lYx3PMSaaXnbbfHY5Bm")
    @GET("60fea3c4a917050205d07e1e")
    suspend fun getDogs(): List<DogNetworkEntity>

    /**
     * Make connection with the API
     */
    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectInterceptor
        ): AnimalApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.jsonbin.io/b/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AnimalApiService::class.java)
        }
    }
}