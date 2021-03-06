package com.example.petadopt.data.network.animals.response

import com.example.petadopt.data.domain.Image

data class CatNetworkEntity (
    var id: String,
    var name: String,
    var breed: String,
    var sex: String,
    var birthday: String,
    var urgent: String,
    var height: String,
    var description: String,
    var images: List<String>
)