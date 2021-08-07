package com.example.petadopt.data.database.animals.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CatWithImages(
    @Embedded val cat: CatDatabaseEntity,
    @Relation(
        parentColumn = "cat_id",
        entityColumn = "id"
    )
    val images: List<ImageDatabaseEntity>
)