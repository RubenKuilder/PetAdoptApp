package com.example.petadopt.data.network.animals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.petadopt.data.network.animals.response.AnimalsNetworkEntity

class AnimalsNetworkDataSource(
    private val animalsApiService: AnimalsApiService
) {
    private val _downloadedAnimals = MutableLiveData<AnimalsNetworkEntity>()
    val downloadedAnimalsNetworkEntity: LiveData<AnimalsNetworkEntity>
        get() = _downloadedAnimals

    /**
     * Download Animals from AnimalApiService
     */
    suspend fun downloadAnimals() {
        try {
            Log.i("Debug", "DataSource - downloadAnimals()")
            // Download the data from the API
            val downloadedAnimals = animalsApiService.getAnimals()
            Log.i("Debug", "DataSource - downloadAnimals() $downloadedAnimals")

            // Post fetched dogs to the MutableLiveData field
            _downloadedAnimals.postValue(downloadedAnimals)
        } catch (e: Exception) {
            Log.e("Debug", "$e")
        }
    }
}