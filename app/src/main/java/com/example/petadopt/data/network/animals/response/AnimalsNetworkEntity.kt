package com.example.petadopt.data.network.animals.response

data class AnimalsNetworkEntity (
    var dogs: List<DogNetworkEntity>,
    var cats: List<CatNetworkEntity>,
    var rabbits: List<RabbitNetworkEntity>
)