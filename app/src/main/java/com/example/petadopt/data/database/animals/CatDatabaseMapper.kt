package com.example.petadopt.data.database.animals

import com.example.petadopt.data.database.animals.entities.CatDatabaseEntity
import com.example.petadopt.data.database.animals.entities.CatWithImages
import com.example.petadopt.data.database.animals.entities.DogWithImages
import com.example.petadopt.data.domain.Cat
import com.example.petadopt.data.domain.Dog
import com.example.petadopt.utilities.EntityMapper
import javax.inject.Inject

class CatDatabaseMapper
@Inject
constructor(
    private var imageDatabaseMapper: ImageDatabaseMapper
): EntityMapper<CatDatabaseEntity, Cat> {
    override fun mapFromEntity(entity: CatDatabaseEntity): Cat {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(domainModel: Cat): CatDatabaseEntity {
        return CatDatabaseEntity(
            cat_id = domainModel.id,
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

    fun mapFromDogWithImage(entity: CatWithImages): Cat {
        return Cat(
            id = entity.cat.cat_id,
            name = entity.cat.name,
            breed = entity.cat.breed,
            sex = entity.cat.sex,
            birthday = entity.cat.birthday,
            urgent = entity.cat.urgent,
            height = entity.cat.height,
            description = entity.cat.description,
            images = imageDatabaseMapper.mapFromEntityList(entity.images),
            isFavourite = entity.cat.isFavourite
        )
    }

    fun mapFromEntityList(entities: List<CatWithImages>): List<Cat> {
        return entities.map { mapFromDogWithImage(it) }
    }
}