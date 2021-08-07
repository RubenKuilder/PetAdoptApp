package com.example.petadopt.data.database.animals.entities

import androidx.room.Embedded
import androidx.room.Relation

data class RabbitWithImages(
    @Embedded val rabbit: RabbitDatabaseEntity,
    @Relation(
        parentColumn = "rabbit_id",
        entityColumn = "id"
    )
    val images: List<ImageDatabaseEntity>
)