package com.example.petadopt.data.database.animals.entities

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cat")
data class CatDatabaseEntity(
    @PrimaryKey(autoGenerate = false)
    var cat_id: String,
    var name: String,
    var breed: String,
    var sex: String,
    var birthday: String,
    var urgent: String,
    var height: String,
    var description: String,

    @ColumnInfo(defaultValue = "0")
    var isFavourite: Boolean
)