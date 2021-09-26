package com.example.petadopt.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.petadopt.data.database.animals.entities.CatDatabaseEntity
import com.example.petadopt.data.database.animals.entities.DogDatabaseEntity
import com.example.petadopt.data.database.animals.entities.ImageDatabaseEntity
import com.example.petadopt.data.database.animals.entities.RabbitDatabaseEntity

@Database(entities = [DogDatabaseEntity::class, CatDatabaseEntity::class, RabbitDatabaseEntity::class, ImageDatabaseEntity::class], version = 5)
abstract class Database: RoomDatabase() {
    abstract fun dao(): Dao

    companion object {
        const val DATABASE_NAME: String = "database"
    }
}