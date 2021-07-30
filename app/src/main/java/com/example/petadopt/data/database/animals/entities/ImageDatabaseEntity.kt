package com.example.petadopt.data.database.animals.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Image")
data class ImageDatabaseEntity(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var url: String
)