package com.example.petadopt.data.network.animals

import com.example.petadopt.data.domain.Cat
import com.example.petadopt.data.domain.Dog
import com.example.petadopt.data.domain.Image
import com.example.petadopt.data.network.animals.response.AnimalsNetworkEntity
import com.example.petadopt.data.network.animals.response.CatNetworkEntity
import com.example.petadopt.data.network.animals.response.DogNetworkEntity
import com.example.petadopt.utilities.EntityMapper
import javax.inject.Inject

class CatNetworkMapper
@Inject
constructor(): EntityMapper<CatNetworkEntity, Cat>{
    override fun mapFromEntity(entity: CatNetworkEntity): Cat {
        var images: MutableList<Image> = mutableListOf()
        entity.images.forEach {
            images.add(Image(entity.id, it))
        }

        return Cat(
            id = entity.id,
            name = entity.name,
            breed = entity.breed,
            sex = entity.sex,
            birthday = entity.birthday,
            urgent = entity.urgent,
            height = entity.height,
            description = entity.description,
            images = images
        )
    }

    override fun mapToEntity(domainModel: Cat): CatNetworkEntity {
        var images: MutableList<String> = mutableListOf()
        domainModel.images.forEach {
            images.add(it.url)
        }

        return CatNetworkEntity(
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

    fun mapFromEntityList(entities: List<CatNetworkEntity>): List<Cat> {
        return entities.map{mapFromEntity(it)}
    }

    fun mapToEntityList(entities: List<Cat>): List<CatNetworkEntity> {
        return entities.map{mapToEntity(it)}
    }
}