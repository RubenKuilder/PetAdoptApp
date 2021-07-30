package com.example.petadopt.di

import com.example.petadopt.data.network.animals.AnimalApiService
import com.example.petadopt.utilities.EntityMapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    // Dit heb ik nu in AnimalApiService.
    // Waar is het het mooist om dit neer te zetten?
//    @Singleton
//    @Provides
//    fun provideGsonBuilder(): Gson {
//        return GsonBuilder().create()
//    }
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(gson: Gson): Retrofit.Builder {
//        return Retrofit.Builder()
//            .baseUrl("https://api.jsonbin.io/b/")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//    }
//
//    @Singleton
//    @Provides
//    fun provideAnimalApiService(retrofit: Retrofit.Builder): AnimalApiService {
//        return retrofit
//            .build()
//            .create(AnimalApiService::class.java)
//    }
}