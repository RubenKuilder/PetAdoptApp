package com.example.petadopt.data.database.animals.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Rabbit")
data class RabbitDatabaseEntity(
    @PrimaryKey(autoGenerate = false)
    var rabbit_id: String,
    var name: String,
    var breed: String,
    var sex: String,
    var birthday: String,
    var urgent: String,
    var height: String,
    var description: String
)