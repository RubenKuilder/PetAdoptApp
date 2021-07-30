package com.example.petadopt.data.network.animals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.petadopt.data.network.animals.response.DogNetworkEntity

class DogNetworkDataSourceImpl(
    private val animalApiService: AnimalApiService
): DogNetworkDataSource {
    private val _downloadedDogs = MutableLiveData<List<DogNetworkEntity>>()

    override val downloadedDogNetworkEntities: LiveData<List<DogNetworkEntity>>
        get() = _downloadedDogs

    /**
     * Fetch Dogs from AnimalApiService
     */
    override suspend fun downloadDogs() {
        try {
            // Download the data from the API
            val fetchedDogs = animalApiService.getDogs()

            // Post fetched dogs to the MutableLiveData field
            _downloadedDogs.postValue(fetchedDogs)
        } catch (e: Exception) {
            Log.e("DogNetworkDataSourceImpl", e.toString())
        }
    }
}