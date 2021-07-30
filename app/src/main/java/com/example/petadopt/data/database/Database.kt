package com.example.petadopt.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.petadopt.data.database.animals.entities.DogDatabaseEntity
import com.example.petadopt.data.database.animals.entities.ImageDatabaseEntity

@Database(entities = [DogDatabaseEntity::class, ImageDatabaseEntity::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun dao(): Dao

    companion object {
        val DATABASE_NAME: String = "database"
    }
}