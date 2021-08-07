package com.example.petadopt.data.network.animals

import com.example.petadopt.data.domain.Animals
import com.example.petadopt.data.domain.Cat
import com.example.petadopt.data.network.animals.response.AnimalsNetworkEntity
import com.example.petadopt.data.network.animals.response.CatNetworkEntity
import com.example.petadopt.utilities.EntityMapper
import javax.inject.Inject

class AnimalsNetworkMapper
@Inject
constructor(): EntityMapper<AnimalsNetworkEntity, Animals> {
    override fun mapFromEntity(entity: AnimalsNetworkEntity): Animals {
        return Animals(
            dogs = DogNetworkMapper().mapFromEntityList(entity.dogs),
            cats = CatNetworkMapper().mapFromEntityList(entity.cats),
            rabbits = RabbitNetworkMapper().mapFromEntityList(entity.rabbits)
        )
    }

    override fun mapToEntity(domainModel: Animals): AnimalsNetworkEntity {
        return AnimalsNetworkEntity(
            dogs = DogNetworkMapper().mapToEntityList(domainModel.dogs),
            cats = CatNetworkMapper().mapToEntityList(domainModel.cats),
            rabbits = RabbitNetworkMapper().mapToEntityList(domainModel.rabbits)
        )
    }
}