package com.example.petadopt.data.network.animals

import androidx.lifecycle.LiveData
import com.example.petadopt.data.network.animals.response.DogNetworkEntity

interface DogNetworkDataSource {
    val downloadedDogNetworkEntities: LiveData<List<DogNetworkEntity>>

    suspend fun downloadDogs()
}