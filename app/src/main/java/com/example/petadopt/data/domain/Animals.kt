package com.example.petadopt.data.domain

//TODO: Make this a singleton?
// Are data classes singleton by default?
data class Animals(
    var dogs: List<Dog>,
    var cats: List<Cat>,
    var rabbits: List<Rabbit>
)
