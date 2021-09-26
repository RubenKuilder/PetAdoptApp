package com.example.petadopt.data.network.animals

import com.example.petadopt.data.database.animals.ImageDatabaseMapper
import com.example.petadopt.data.domain.Dog
import com.example.petadopt.data.domain.Image
import com.example.petadopt.data.network.animals.response.DogNetworkEntity
import com.example.petadopt.utilities.EntityMapper
import javax.inject.Inject

class DogNetworkMapper
@Inject
constructor() : EntityMapper<DogNetworkEntity, Dog> {
    //TODO: In de klasse die deze klasse gebruikt moet ik dus verschillende functies hebben.
    // Bijv getDog() die mapFromEntityList() aanroept
    // OF
    // Bijv getDog() die in een forEach op de AnimalsNetworkMapper.dogs DogNetworkMapper.mapFromEntity(it) aanroept

    override fun mapFromEntity(entity: DogNetworkEntity): Dog {
        var images: MutableList<Image> = mutableListOf()
        entity.images.forEach {
            images.add(Image(entity.id, it))
        }

        return Dog(
            id = entity.id,
            name = entity.name,
            breed = entity.breed,
            sex = entity.sex,
            birthday = entity.birthday,
            urgent = entity.urgent,
            height = entity.height,
            description = entity.description,
            images = images,
            isFavourite = false
        )
    }

    override fun mapToEntity(domainModel: Dog): DogNetworkEntity {
        var images: MutableList<String> = mutableListOf()
        domainModel.images.forEach {
            images.add(it.url)
        }

        return DogNetworkEntity(
            id = domainModel.id,
            name = domainModel.name,
            breed = domainModel.breed,
            sex = domainModel.sex,
            birthday = domainModel.birthday,
            urgent = domainModel.urgent,
            height = domainModel.height,
            description = domainModel.description,
            images = images
        )
    }

    fun mapFromEntityList(entities: List<DogNetworkEntity>): List<Dog> {
        return entities.map{mapFromEntity(it)}
    }

    fun mapToEntityList(entities: List<Dog>): List<DogNetworkEntity> {
        return entities.map{mapToEntity(it)}
    }
}