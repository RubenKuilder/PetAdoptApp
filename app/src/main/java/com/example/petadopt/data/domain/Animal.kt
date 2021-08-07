package com.example.petadopt.data.domain

interface Animal {
    val type: Int

    var id: String
    var name: String
    var breed: String
    var sex: String
    var birthday: String
    var urgent: String
    var height: String
    var description: String
    var images: List<Image>

    companion object {
        const val TYPE_DOG = 101
        const val TYPE_CAT = 102
        const val TYPE_RABBIT = 103
    }
}