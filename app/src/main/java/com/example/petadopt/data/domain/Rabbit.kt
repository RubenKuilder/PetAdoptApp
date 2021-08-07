package com.example.petadopt.data.domain

import com.example.petadopt.data.domain.Animal.Companion.TYPE_RABBIT

data class Rabbit(
    override var id: String,
    override var name: String,
    override var breed: String,
    override var sex: String,
    override var birthday: String,
    override var urgent: String,
    override var height: String,
    override var description: String,
    override var images: List<Image>
): Animal {
    override val type: Int
        get() = TYPE_RABBIT
}