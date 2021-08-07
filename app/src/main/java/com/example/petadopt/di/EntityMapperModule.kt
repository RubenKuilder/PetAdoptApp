package com.example.petadopt.di

import com.example.petadopt.data.database.animals.ImageDatabaseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EntityMapperModule {
    @Singleton
    @Provides
    fun provideImageDatabaseMapper(): ImageDatabaseMapper {
        return ImageDatabaseMapper()
    }
}