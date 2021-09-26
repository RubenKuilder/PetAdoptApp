package com.example.petadopt.data.network.animals

import com.example.petadopt.data.domain.Image
import com.example.petadopt.data.domain.Rabbit
import com.example.petadopt.data.network.animals.response.RabbitNetworkEntity
import com.example.petadopt.utilities.EntityMapper
import javax.inject.Inject

class RabbitNetworkMapper
@Inject
constructor(): EntityMapper<RabbitNetworkEntity, Rabbit> {
    override fun mapFromEntity(entity: RabbitNetworkEntity): Rabbit {
        var images: MutableList<Image> = mutableListOf()
        entity.images.forEach {
            images.add(Image(entity.id, it))
        }

        return Rabbit(
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

    override fun mapToEntity(domainModel: Rabbit): RabbitNetworkEntity {
        var images: MutableList<String> = mutableListOf()
        domainModel.images.forEach {
            images.add(it.url)
        }

        return RabbitNetworkEntity(
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

    fun mapFromEntityList(entities: List<RabbitNetworkEntity>): List<Rabbit> {
        return entities.map{mapFromEntity(it)}
    }

    fun mapToEntityList(entities: List<Rabbit>): List<RabbitNetworkEntity> {
        return entities.map{mapToEntity(it)}
    }
}