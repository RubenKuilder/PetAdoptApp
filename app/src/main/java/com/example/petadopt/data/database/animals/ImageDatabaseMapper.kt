package com.example.petadopt.data.database.animals

import com.example.petadopt.data.database.animals.entities.ImageDatabaseEntity
import com.example.petadopt.data.domain.Animals
import com.example.petadopt.data.domain.Dog
import com.example.petadopt.data.domain.Image
import com.example.petadopt.data.network.animals.response.DogNetworkEntity
import com.example.petadopt.utilities.EntityMapper
import javax.inject.Inject

class ImageDatabaseMapper
@Inject
constructor(): EntityMapper<ImageDatabaseEntity, Image>{
    override fun mapFromEntity(entity: ImageDatabaseEntity): Image {
        return Image(
            id = entity.id,
            url = entity.url
        )
    }

    override fun mapToEntity(domainModel: Image): ImageDatabaseEntity {
        return ImageDatabaseEntity(
            id = domainModel.id,
            url = domainModel.url
        )
    }

    fun mapFromEntityList(entities: List<ImageDatabaseEntity>): List<Image> {
        return entities.map{mapFromEntity(it)}
    }

    fun mapToEntityList(entities: List<Image>): List<ImageDatabaseEntity> {
        return entities.map{mapToEntity(it)}
    }
}