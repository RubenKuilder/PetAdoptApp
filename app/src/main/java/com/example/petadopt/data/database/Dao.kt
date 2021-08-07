package com.example.petadopt.data.database

import androidx.room.*
import androidx.room.Dao
import com.example.petadopt.data.database.animals.entities.*

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertDogDatabaseEntity(dogDatabaseEntity: DogDatabaseEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCatDatabaseEntity(catDatabaseEntity: CatDatabaseEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertRabbitDatabaseEntity(rabbitDatabaseEntity: RabbitDatabaseEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertImageDatabaseEntity(imageDatabaseEntity: ImageDatabaseEntity): Long

    @Transaction
    @Query("SELECT * FROM Dog")
    suspend fun getDogsWithImages(): List<DogWithImages>

    @Transaction
    @Query("SELECT * FROM Cat")
    suspend fun getCatsWithImages(): List<CatWithImages>

    @Transaction
    @Query("SELECT * FROM Rabbit")
    suspend fun getRabbitsWithImages(): List<RabbitWithImages>
}