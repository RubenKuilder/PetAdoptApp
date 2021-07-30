package com.example.petadopt.di

import com.example.petadopt.data.database.Dao
import com.example.petadopt.data.database.animals.AnimalsDatabaseMapper
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
    fun provideAnimalsNetworkDataSource(animalsApiService: AnimalsApiService): AnimalsNetworkDataSource {
        return AnimalsNetworkDataSourceImpl(animalsApiService)
    }

    @Singleton
    @Provides
    fun provideAnimalsRepository(
        dao: Dao,
        animalsNetworkDataSource: AnimalsNetworkDataSource,
        animalsNetworkMapper: AnimalsNetworkMapper,
        databaseMapper: AnimalsDatabaseMapper
    ): AnimalsRepository {
        return AnimalsRepository(dao, animalsNetworkDataSource, animalsNetworkMapper, databaseMapper)
    }
}