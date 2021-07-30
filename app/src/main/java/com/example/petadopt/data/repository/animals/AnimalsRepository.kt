package com.example.petadopt.data.repository.animals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.petadopt.data.database.Dao
import com.example.petadopt.data.database.animals.AnimalsDatabaseMapper
import com.example.petadopt.data.domain.Animals
import com.example.petadopt.data.domain.Cat
import com.example.petadopt.data.domain.Dog
import com.example.petadopt.data.domain.Rabbit
import com.example.petadopt.data.network.animals.*
import com.example.petadopt.data.network.animals.response.AnimalsNetworkEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnimalsRepository
constructor(
    private val dao: Dao,
    private val animalsNetworkDataSource: AnimalsNetworkDataSource,
    private val animalsNetworkMapper: AnimalsNetworkMapper,
    private val databaseMapper: AnimalsDatabaseMapper
) {
    /*
    private val _animals = MutableLiveData<Animals>()
    val animals: LiveData<Animals>
        get() = _animals

    /**
     * Initialize observable
     */
    init {
        animalsNetworkDataSource.downloadedDogNetworkEntities.observeForever { animals ->
            Log.i("Debug", "AnimalsRepository-init")
            persistFetchedAnimals(animals)
        }
    }

    /**
     * Save downloaded data to variable
     *
     * @param fetchedDogs the dogs fetched in AnimalApiService
     */
    private fun persistFetchedAnimals(downloadedAnimals: AnimalsNetworkEntity) {
        Log.i("Debug", "AnimalsRepository-persist")
        GlobalScope.launch(Dispatchers.IO) {
            var dogs: List<Dog> = dogNetworkMapper.mapFromEntityList(downloadedAnimals.dogs)
            var cats: List<Cat> = catNetworkMapper.mapFromEntityList(downloadedAnimals.cats)
            var rabbits: List<Rabbit> = rabbitNetworkMapper.mapFromEntityList(downloadedAnimals.rabbits)

            val animals = Animals(dogs, cats, rabbits)
            Log.i("AnimalsRepository", "$animals")
            _animals.postValue(animals)
        }
    }
     */

    suspend fun getAnimals(): LiveData<Animals> {
        Log.i("Debug", "repository - getAnimals()")
        val networkAnimals = animalsNetworkDataSource.downloadAnimals()
        Log.i("Debug", "repository - $networkAnimals")
        val animals = animalsNetworkMapper.mapFromEntity(networkAnimals)
        return MutableLiveData<Animals>(animals)
    }

    /*
    private var timeFetched = ZonedDateTime.now().minusHours(1)

    /**
     * Initialize observable
     */
    init {
        dogNetworkDataSource.downloadedDogNetworkEntities.observeForever { dogs ->
            persistFetchedDogs(dogs)
        }
    }

    /**
     * Get the dogs data from the database
     */
    suspend fun getDogs(): LiveData<List<Dog>> {
        return withContext(Dispatchers.IO) {
            // Check if data needs to be downloaded or if it can be fetched
            initDogData()

            // Retrieve data from the database
            val dogData = MutableLiveData(databaseMapper.mapDogWithImagesListToDogList(dao.getDogsWithImages()))
            return@withContext dogData
        }
    }

    /**
     * Upsert downloaded data to database
     *
     * @param fetchedDogs the dogs fetched in AnimalApiService
     */
    private fun persistFetchedDogs(downloadedDogs: List<DogNetworkEntity>) {
        GlobalScope.launch(Dispatchers.IO) {
            val dogs = networkMapper.mapFromEntityList(downloadedDogs)

            for(dog in dogs) {
                // Send downloaded data to the database
                dao.upsertDogDatabaseEntity(databaseMapper.mapDogToDogDatabaseEntity(dog))

                val imageList = databaseMapper.mapImageToImageDatabaseEntity(dog)
                for (image in imageList) {
                    dao.upsertImageDatabaseEntity(image)
                }
            }
        }
    }

    /**
     * Check if download is necessary. If download is needed, download data and set timeFetched
     */
    private suspend fun initDogData() {
        // Check if download is necessary
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        val isDownloadCurrentlyNeeded = timeFetched.isBefore(thirtyMinutesAgo)

        // Download complete dog list
        if(isDownloadCurrentlyNeeded) {
            dogNetworkDataSource.downloadDogs()

            // Set new fetched time
            timeFetched = ZonedDateTime.now()
        }
    }
     */
}