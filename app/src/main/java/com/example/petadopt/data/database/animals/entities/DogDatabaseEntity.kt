package com.example.petadopt.data.database.animals.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Dog")
data class DogDatabaseEntity(
    @PrimaryKey(autoGenerate = false)
    var dog_id: String,
    var name: String,
    var breed: String,
    var sex: String,
    var birthday: String,
    var urgent: String,
    var height: String,
    var description: String
)