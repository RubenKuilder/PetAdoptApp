package com.example.petadopt.data.domain

data class Cat(
    var id: String,
    var name: String,
    var breed: String,
    var sex: String,
    var birthday: String,
    var urgent: String,
    var height: String,
    var description: String,
    var images: List<Image>
)