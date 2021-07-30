package com.example.petadopt.data.network.animals

import androidx.lifecycle.LiveData
import com.example.petadopt.data.network.animals.response.AnimalsNetworkEntity
import com.example.petadopt.data.network.animals.response.DogNetworkEntity

interface AnimalsNetworkDataSource {
    suspend fun downloadAnimals(): AnimalsNetworkEntity
}