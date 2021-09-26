package com.example.petadopt.data.repository.animals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.petadopt.data.database.Dao
import com.example.petadopt.data.database.animals.CatDatabaseMapper
import com.example.petadopt.data.database.animals.DogDatabaseMapper
import com.example.petadopt.data.database.animals.ImageDatabaseMapper
import com.example.petadopt.data.database.animals.RabbitDatabaseMapper
import com.example.petadopt.data.domain.*
import com.example.petadopt.data.network.animals.*
import com.example.petadopt.data.network.animals.response.AnimalsNetworkEntity
import com.example.petadopt.utilities.DataState
import com.example.petadopt.utilities.Utils.Companion.TYPE_CAT
import com.example.petadopt.utilities.Utils.Companion.TYPE_DOG
import com.example.petadopt.utilities.Utils.Companion.TYPE_RABBIT
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.time.ZonedDateTime

class AnimalsRepository
constructor(
    private val dao: Dao,
    private val animalsApiService: AnimalsApiService,
    private val animalsNetworkMapper: AnimalsNetworkMapper,
    private val dogDatabaseMapper: DogDatabaseMapper,
    private val catDatabaseMapper: CatDatabaseMapper,
    private val rabbitDatabaseMapper: RabbitDatabaseMapper,
    private val imageDatabaseMapper: ImageDatabaseMapper
) {
    private var timeFetched = ZonedDateTime.now().minusMinutes(60)

    /**
     * This method is supposed to retrieve the data from the database
     */
    suspend fun getAnimals(hardRefresh: Boolean): Flow<DataState<Animals>> = flow {
        try {
            if(isFetchCurrentlyNeeded() || hardRefresh) {
                if(!hardRefresh) {
                    emit(DataState.Loading)
                }

                // Update timeFetched
                timeFetched = ZonedDateTime.now()

                // Get the data from the API
                val networkAnimals = animalsApiService.getAnimals()

                // Persist fetched data into Database
                persistFetchedAnimals(networkAnimals)
            }

            // Get the data from the database
            val dogs = dogDatabaseMapper.mapFromEntityList(dao.getDogsWithImages())
            val cats = catDatabaseMapper.mapFromEntityList(dao.getCatsWithImages())
            val rabbits = rabbitDatabaseMapper.mapFromEntityList(dao.getRabbitsWithImages())
            val animals = Animals(dogs, cats, rabbits)

            emit(DataState.Success(animals))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    /**
     * This method is supposed to insert the network data into the database
     */
    private suspend fun persistFetchedAnimals(fetchedAnimals: AnimalsNetworkEntity) {
        val animals = animalsNetworkMapper.mapFromEntity(fetchedAnimals)

        // TODO: Add better try catch around each outer for loop
        try {
            // Iterate over every dog in the list
            for (dog in animals.dogs) {
                // Update/insert the dog entity
                dao.upsertDogDatabaseEntity(dogDatabaseMapper.mapToEntity(dog))

                // Iterate over every image in the list
                for (image in dog.images) {
                    // Update/insert the image entity
                    dao.upsertImageDatabaseEntity(imageDatabaseMapper.mapToEntity(image))
                }
            }
        } catch (e: Exception) {
            Log.i("Debug", "exception: $e")
        }

        try {
            // Iterate over every cat in the list
            for (cat in animals.cats) {
                // Update/insert the dog entity
                dao.upsertCatDatabaseEntity(catDatabaseMapper.mapToEntity(cat))

                // Iterate over every image in the list
                for (image in cat.images) {
                    // Update/insert the image entity
                    dao.upsertImageDatabaseEntity(imageDatabaseMapper.mapToEntity(image))
                }
            }
        } catch (e: Exception) {
            Log.i("Debug", "exception: $e")
        }

        try {
            // Iterate over every cat in the list
            for (rabbit in animals.rabbits) {
                // Update/insert the dog entity
                dao.upsertRabbitDatabaseEntity(rabbitDatabaseMapper.mapToEntity(rabbit))

                // Iterate over every image in the list
                for (image in rabbit.images) {
                    // Update/insert the image entity
                    dao.upsertImageDatabaseEntity(imageDatabaseMapper.mapToEntity(image))
                }
            }
        } catch (e: Exception) {
            Log.i("Debug", "exception: $e")
        }
    }

    suspend fun updateIsFavourite(animal: Animal) {
        when (animal.type) {
            TYPE_DOG -> {
                dao.updateIsFavouriteDogDatabaseEntity(animal.id)
            }
            TYPE_CAT -> {
                dao.updateIsFavouriteCatDatabaseEntity(animal.id)
            }
            TYPE_RABBIT -> {
                dao.updateIsFavouriteRabbitDatabaseEntity(animal.id)
            }
        }
    }

    suspend fun getSingleAnimal(id: String, type: Int): Flow<DataState<Animal>?> = flow {
        try {
            emit(DataState.Loading)
            //TODO: Add working progressBar in animal_detail_fragment.xml
            //TODO: Add the material design placeholder animations
//            delay(2000)

            when (type) {
                TYPE_DOG -> {
                    emit(
                        DataState.Success(
                            dogDatabaseMapper.mapFromDogWithImage(dao.getSingleDogWithImages(id))
                        )
                    )
                }
                TYPE_CAT -> {
                    emit(
                        DataState.Success(
                            catDatabaseMapper.mapFromDogWithImage(dao.getSingleCatWithImages(id))
                        )
                    )
                }
                TYPE_RABBIT -> {
                    emit(
                        DataState.Success(
                            rabbitDatabaseMapper.mapFromDogWithImage(
                                dao.getSingleRabbitWithImages(
                                    id
                                )
                            )
                        )
                    )
                }
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    private fun isFetchCurrentlyNeeded(): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)

        return timeFetched.isBefore(thirtyMinutesAgo)
    }
}