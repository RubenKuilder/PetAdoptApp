package com.example.petadopt.data.database.animals

import com.example.petadopt.data.database.animals.entities.DogDatabaseEntity
import com.example.petadopt.data.database.animals.entities.DogWithImages
import com.example.petadopt.data.database.animals.entities.ImageDatabaseEntity
import com.example.petadopt.data.domain.Dog
import com.example.petadopt.data.domain.Image
import com.example.petadopt.data.network.animals.response.DogNetworkEntity
import com.example.petadopt.utilities.EntityMapper
import javax.inject.Inject

class DogDatabaseMapper
@Inject
constructor(
    private var imageDatabaseMapper: ImageDatabaseMapper
): EntityMapper<DogDatabaseEntity, Dog> {
    override fun mapFromEntity(entity: DogDatabaseEntity): Dog {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(domainModel: Dog): DogDatabaseEntity {
        return DogDatabaseEntity(
            dog_id = domainModel.id,
            name = domainModel.name,
            breed = domainModel.breed,
            sex = domainModel.sex,
            birthday = domainModel.birthday,
            urgent = domainModel.urgent,
            height = domainModel.height,
            description = domainModel.description
        )
    }

    fun mapFromDogWithImage(entity: DogWithImages): Dog {
        return Dog(
            id = entity.dog.dog_id,
            name = entity.dog.name,
            breed = entity.dog.breed,
            sex = entity.dog.sex,
            birthday = entity.dog.birthday,
            urgent = entity.dog.urgent,
            height = entity.dog.height,
            description = entity.dog.description,
            images = imageDatabaseMapper.mapFromEntityList(entity.images)
        )
    }

    fun mapFromEntityList(entities: List<DogWithImages>): List<Dog> {
        return entities.map{mapFromDogWithImage(it)}
    }
}