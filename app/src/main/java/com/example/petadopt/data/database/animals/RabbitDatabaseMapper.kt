package com.example.petadopt.data.database.animals

import com.example.petadopt.data.database.animals.entities.CatDatabaseEntity
import com.example.petadopt.data.database.animals.entities.CatWithImages
import com.example.petadopt.data.database.animals.entities.RabbitDatabaseEntity
import com.example.petadopt.data.database.animals.entities.RabbitWithImages
import com.example.petadopt.data.domain.Cat
import com.example.petadopt.data.domain.Rabbit
import com.example.petadopt.utilities.EntityMapper
import javax.inject.Inject

class RabbitDatabaseMapper
@Inject
constructor(
    private var imageDatabaseMapper: ImageDatabaseMapper
): EntityMapper<RabbitDatabaseEntity, Rabbit> {
    override fun mapFromEntity(entity: RabbitDatabaseEntity): Rabbit {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(domainModel: Rabbit): RabbitDatabaseEntity {
        return RabbitDatabaseEntity(
            rabbit_id = domainModel.id,
            name = domainModel.name,
            breed = domainModel.breed,
            sex = domainModel.sex,
            birthday = domainModel.birthday,
            urgent = domainModel.urgent,
            height = domainModel.height,
            description = domainModel.description,
            isFavourite = domainModel.isFavourite
        )
    }

    fun mapFromDogWithImage(entity: RabbitWithImages): Rabbit {
        return Rabbit(
            id = entity.rabbit.rabbit_id,
            name = entity.rabbit.name,
            breed = entity.rabbit.breed,
            sex = entity.rabbit.sex,
            birthday = entity.rabbit.birthday,
            urgent = entity.rabbit.urgent,
            height = entity.rabbit.height,
            description = entity.rabbit.description,
            images = imageDatabaseMapper.mapFromEntityList(entity.images),
            isFavourite = entity.rabbit.isFavourite
        )
    }

    fun mapFromEntityList(entities: List<RabbitWithImages>): List<Rabbit> {
        return entities.map { mapFromDogWithImage(it) }
    }
}