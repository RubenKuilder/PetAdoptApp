package com.example.petadopt.di

import com.example.petadopt.data.database.Dao
import com.example.petadopt.data.database.animals.CatDatabaseMapper
import com.example.petadopt.data.database.animals.DogDatabaseMapper
import com.example.petadopt.data.database.animals.ImageDatabaseMapper
import com.example.petadopt.data.database.animals.RabbitDatabaseMapper
import com.example.petadopt.data.network.animals.*
import com.example.petadopt.data.repository.animals.AnimalsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideAnimalsApiService(): AnimalsApiService {
        return AnimalsApiService()
    }

    @Singleton
    @Provides
    fun provideAnimalsRepository(
        dao: Dao,
        animalsApiService: AnimalsApiService,
        animalsNetworkMapper: AnimalsNetworkMapper,
        dogDatabaseMapper: DogDatabaseMapper,
        catDatabaseMapper: CatDatabaseMapper,
        rabbitDatabaseMapper: RabbitDatabaseMapper,
        imageDatabaseMapper: ImageDatabaseMapper
    ): AnimalsRepository {
        return AnimalsRepository(
            dao,
            animalsApiService,
            animalsNetworkMapper,
            dogDatabaseMapper,
            catDatabaseMapper,
            rabbitDatabaseMapper,
            imageDatabaseMapper
        )
    }
}