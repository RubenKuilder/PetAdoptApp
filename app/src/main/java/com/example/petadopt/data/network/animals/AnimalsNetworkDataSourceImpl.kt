package com.example.petadopt.data.network.animals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.petadopt.data.network.animals.response.AnimalsNetworkEntity

class AnimalsNetworkDataSourceImpl(
    private val animalsApiService: AnimalsApiService
): AnimalsNetworkDataSource {
    /**
     * Fetch Dogs from AnimalApiService
     */
    override suspend fun downloadAnimals(): AnimalsNetworkEntity {
        Log.i("Debug", "DataSource - downloadAnimals()")
        // Download the data from the API
        val downloadedAnimals = animalsApiService.getAnimals()

        // Post fetched dogs to the MutableLiveData field
        return downloadedAnimals
    }
}