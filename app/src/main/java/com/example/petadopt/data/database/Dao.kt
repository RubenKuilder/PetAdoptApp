package com.example.petadopt.data.database

import androidx.room.*
import androidx.room.Dao
import com.example.petadopt.data.database.animals.entities.DogDatabaseEntity
import com.example.petadopt.data.database.animals.entities.DogWithImages
import com.example.petadopt.data.database.animals.entities.ImageDatabaseEntity

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertDogDatabaseEntity(dogDatabaseEntity: DogDatabaseEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertImageDatabaseEntity(imageDatabaseEntity: ImageDatabaseEntity): Long

    @Transaction
    @Query("SELECT * FROM Dog")
    suspend fun getDogsWithImages(): List<DogWithImages>
}