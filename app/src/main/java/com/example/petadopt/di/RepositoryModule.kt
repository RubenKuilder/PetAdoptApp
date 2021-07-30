package com.example.petadopt.di

import com.example.petadopt.data.database.Dao
import com.example.petadopt.data.database.animals.AnimalsDatabaseMapper
import com.example.petadopt.data.network.animals.AnimalApiService
import com.example.petadopt.data.network.animals.DogNetworkDataSource
import com.example.petadopt.data.network.animals.DogNetworkMapper
import com.example.petadopt.data.repository.animals.DogRepository
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
    fun provideDogRepository(
        dao: Dao,
        dogNetworkDataSource: DogNetworkDataSource,
        networkMapper: DogNetworkMapper,
        databaseMapper: AnimalsDatabaseMapper
    ): DogRepository {
        return DogRepository(dao, dogNetworkDataSource, networkMapper, databaseMapper)
    }
}