package com.example.petadopt.data.database.animals.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class DogWithImages(
    @Embedded val dog: DogDatabaseEntity,
    @Relation(
        parentColumn = "dog_id",
        entityColumn = "id"
    )
    val images: List<ImageDatabaseEntity>
)