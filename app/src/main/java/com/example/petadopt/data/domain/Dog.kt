package com.example.petadopt.data.domain

data class Dog (
    override var id: String,
    override var name: String,
    override var breed: String,
    override var sex: String,
    override var birthday: String,
    override var urgent: String,
    override var height: String,
    override var description: String,
    override var images: List<String>
): Animal