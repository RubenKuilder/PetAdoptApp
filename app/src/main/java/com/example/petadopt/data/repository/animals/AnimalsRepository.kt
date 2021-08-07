package com.example.petadopt.data.repository.animals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.petadopt.data.database.Dao
import com.example.petadopt.data.database.animals.CatDatabaseMapper
import com.example.petadopt.data.database.animals.DogDatabaseMapper
import com.example.petadopt.data.database.animals.ImageDatabaseMapper
import com.example.petadopt.data.database.animals.RabbitDatabaseMapper
import com.example.petadopt.data.domain.Animals
import com.example.petadopt.data.network.animals.*
import com.example.petadopt.data.network.animals.response.AnimalsNetworkEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class AnimalsRepository
constructor(
    private val dao: Dao,
    private val animalsNetworkDataSource: AnimalsNetworkDataSource,
    private val animalsNetworkMapper: AnimalsNetworkMapper,
    private val dogDatabaseMapper: DogDatabaseMapper,
    private val catDatabaseMapper: CatDatabaseMapper,
    private val rabbitDatabaseMapper: RabbitDatabaseMapper,
    private val imageDatabaseMapper: ImageDatabaseMapper
) {
    private var timeDownloaded = ZonedDateTime.now().minusMinutes(60)

    /**
     * Initialize observable
     */
    init {
        animalsNetworkDataSource.downloadedAnimalsNetworkEntity.observeForever { animals ->
            persistDownloadedAnimals(animals)
        }
    }

    /**
     * This method is supposed to retrieve the data from the database
     * TODO: Is this supposed to return LiveData<Animals>???
     */
    suspend fun getAnimals(): LiveData<Animals?> {
        return withContext(Dispatchers.IO) {
            Log.i("Debug", "repository - getAnimals()")
            initAnimalsData()

            // TODO: get the actual data here
            // Get each animal type
            // Construct an Animal object
            // Return Animal object

            val dogs = dogDatabaseMapper.mapFromEntityList(dao.getDogsWithImages())
            val cats = catDatabaseMapper.mapFromEntityList(dao.getCatsWithImages())
            val rabbits = rabbitDatabaseMapper.mapFromEntityList(dao.getRabbitsWithImages())
            val animals = MutableLiveData(Animals(dogs, cats, rabbits))
            return@withContext animals
        }
    }

    /**
     * This method is supposed to insert the network data into the database
     */
    fun persistDownloadedAnimals(downloadedAnimals: AnimalsNetworkEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            Log.i("Debug", "repository - persistDownloadedAnimals()")
//            Log.i("Debug", "repository - $downloadedAnimals")
//            animalsData = animalsNetworkMapper.mapFromEntity(downloadedAnimals)

            val animals = animalsNetworkMapper.mapFromEntity(downloadedAnimals)

            // Iterate over every dog in the list
            for(dog in animals.dogs) {
                // Update/insert the dog entity
                dao.upsertDogDatabaseEntity(dogDatabaseMapper.mapToEntity(dog))

                // Iterate over every image in the list
                for (image in dog.images) {
                    // Update/insert the image entity
                    dao.upsertImageDatabaseEntity(imageDatabaseMapper.mapToEntity(image))
                }
            }

            // Iterate over every cat in the list
            for(cat in animals.cats) {
                // Update/insert the dog entity
                dao.upsertCatDatabaseEntity(catDatabaseMapper.mapToEntity(cat))

                // Iterate over every image in the list
                for (image in cat.images) {
                    // Update/insert the image entity
                    dao.upsertImageDatabaseEntity(imageDatabaseMapper.mapToEntity(image))
                }
            }

            // Iterate over every cat in the list
            for(rabbit in animals.rabbits) {
                // Update/insert the dog entity
                dao.upsertRabbitDatabaseEntity(rabbitDatabaseMapper.mapToEntity(rabbit))

                // Iterate over every image in the list
                for (image in rabbit.images) {
                    // Update/insert the image entity
                    dao.upsertImageDatabaseEntity(imageDatabaseMapper.mapToEntity(image))
                }
            }
        }
    }

    /**
     * Download API data and set new value for timeDownloaded
     */
    private suspend fun initAnimalsData() {
        if(isDownloadCurrentlyNeeded()) {
            downloadAnimals()

            // TODO: uncomment this line
//            timeDownloaded = ZonedDateTime.now()
        }
    }

    /**
     * Download complete animals object
     */
    private suspend fun downloadAnimals() {
        animalsNetworkDataSource.downloadAnimals()
    }

    /**
     * Check if downloading of API data is currently needed
     *
     * @return return if timeDownloaded is before -setTime- ago
     * TODO: Set 30 minutes in a constant somewhere
     */
    private fun isDownloadCurrentlyNeeded(): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)

        return timeDownloaded.isBefore(thirtyMinutesAgo)
    }
}