package com.example.petadopt.data.network.animals

import com.example.petadopt.data.domain.Dog
import com.example.petadopt.data.network.animals.response.DogNetworkEntity
import com.example.petadopt.utilities.EntityMapper
import javax.inject.Inject

class DogNetworkMapper
@Inject
constructor() : EntityMapper<DogNetworkEntity, Dog> {
    override fun mapFromEntity(entity: DogNetworkEntity): Dog {
        return Dog(
            id = entity.id,
            name = entity.name,
            breed = entity.breed,
            sex = entity.sex,
            birthday = entity.birthday,
            urgent = entity.urgent,
            height = entity.height,
            description = entity.description,
            images = entity.images
        )
    }

    override fun mapToEntity(domainModel: Dog): DogNetworkEntity {

        return DogNetworkEntity(
            id = domainModel.id,
            name = domainModel.name,
            breed = domainModel.breed,
            sex = domainModel.sex,
            birthday = domainModel.birthday,
            urgent = domainModel.urgent,
            height = domainModel.height,
            description = domainModel.description,
            images = domainModel.images
        )
    }

    fun mapFromEntityList(entities: List<DogNetworkEntity>): List<Dog> {
        return entities.map{mapFromEntity(it)}
    }

    fun mapToEntityList(entities: List<Dog>): List<DogNetworkEntity> {
        return entities.map{mapToEntity(it)}
    }
}