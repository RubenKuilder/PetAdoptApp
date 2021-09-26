package com.example.petadopt.data.domain

import com.example.petadopt.utilities.Utils.Companion.TYPE_DOG

data class Dog (
    override var id: String,
    override var name: String,
    override var breed: String,
    override var sex: String,
    override var birthday: String,
    override var urgent: String,
    override var height: String,
    override var description: String,
    override var images: List<Image>,
    override var isFavourite: Boolean
): Animal {
    override val type: Int
        get() = TYPE_DOG
}