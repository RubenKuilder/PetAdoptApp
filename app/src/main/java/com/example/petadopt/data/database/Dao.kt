package com.example.petadopt.data.database

import androidx.room.*
import androidx.room.Dao
import com.example.petadopt.data.database.animals.entities.*
import com.example.petadopt.data.domain.Animal
import com.example.petadopt.data.domain.Dog

@Dao
interface Dao {
    //TODO: Instead of inserted isFavourite into Dog, add another table Favourite which stores an animal ID (dog_id or cat_id or rabbit_id)
    // I think due to setting the relation in DogWithImages etc. it'd automatically select isFavourite field as well
    // Might need to add an @Insert with onConflict.IGNORE which gets called every time the date is inserted in the database, from the network
    // ^ This is to make sure there are isFavourite tables for each animal in the database to avoid 'cant find blabla'-errors
    // Use an @Insert with onConflict.REPLACE which only gets called when the user pressed the Heart icon to update whether an animal is favourite or not

    //region Dog dao methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogDatabaseEntity(dogDatabaseEntity: DogDatabaseEntity): Long

    @Transaction
    @Query("SELECT * FROM Dog WHERE dog_id = :id")
    suspend fun getSingleDogWithImages(id: String): DogWithImages

    @Query("UPDATE Dog SET name = :name, breed = :breed, sex = :sex, birthday = :birthday, urgent = :urgent, height = :height, description = :description WHERE dog_id = :dog_id")
    suspend fun updateDogDatabaseEntity(dog_id: String, name: String, breed: String, sex: String, birthday: String, urgent: String, height: String, description: String)

    suspend fun upsertDogDatabaseEntity(dogDatabaseEntity: DogDatabaseEntity) {
        if(getSingleDogWithImages(dogDatabaseEntity.dog_id).dog.dog_id == dogDatabaseEntity.dog_id) {
            updateDogDatabaseEntity(
                dogDatabaseEntity.dog_id,
                dogDatabaseEntity.name,
                dogDatabaseEntity.breed,
                dogDatabaseEntity.sex,
                dogDatabaseEntity.birthday,
                dogDatabaseEntity.urgent,
                dogDatabaseEntity.height,
                dogDatabaseEntity.description
            )
        } else {
            insertDogDatabaseEntity(dogDatabaseEntity)
        }
    }

    @Query("UPDATE Dog SET isFavourite = NOT isFavourite WHERE dog_id = :dog_id")
    suspend fun updateIsFavouriteDogDatabaseEntity(dog_id: String)

    @Transaction
    @Query("SELECT * FROM Dog")
    suspend fun getDogsWithImages(): List<DogWithImages>
    //endregion

    //region Cat dao methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatDatabaseEntity(catDatabaseEntity: CatDatabaseEntity): Long

    @Transaction
    @Query("SELECT * FROM Cat WHERE cat_id = :id")
    suspend fun getSingleCatWithImages(id: String): CatWithImages

    @Query("UPDATE Cat SET name = :name, breed = :breed, sex = :sex, birthday = :birthday, urgent = :urgent, height = :height, description = :description WHERE cat_id = :cat_id")
    suspend fun updateCatDatabaseEntity(cat_id: String, name: String, breed: String, sex: String, birthday: String, urgent: String, height: String, description: String)

    suspend fun upsertCatDatabaseEntity(catDatabaseEntity: CatDatabaseEntity) {
        if(getSingleCatWithImages(catDatabaseEntity.cat_id).cat.cat_id == catDatabaseEntity.cat_id) {
            updateDogDatabaseEntity(
                catDatabaseEntity.cat_id,
                catDatabaseEntity.name,
                catDatabaseEntity.breed,
                catDatabaseEntity.sex,
                catDatabaseEntity.birthday,
                catDatabaseEntity.urgent,
                catDatabaseEntity.height,
                catDatabaseEntity.description
            )
        } else {
            insertCatDatabaseEntity(catDatabaseEntity)
        }
    }

    @Query("UPDATE Cat SET isFavourite = NOT isFavourite WHERE cat_id = :cat_id")
    suspend fun updateIsFavouriteCatDatabaseEntity(cat_id: String)

    @Transaction
    @Query("SELECT * FROM Cat")
    suspend fun getCatsWithImages(): List<CatWithImages>
    //endregion  data

    //region Rabbit dao methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRabbitDatabaseEntity(rabbitDatabaseEntity: RabbitDatabaseEntity): Long

    @Transaction
    @Query("SELECT * FROM Rabbit WHERE rabbit_id = :id")
    suspend fun getSingleRabbitWithImages(id: String): RabbitWithImages

    @Query("UPDATE Rabbit SET name = :name, breed = :breed, sex = :sex, birthday = :birthday, urgent = :urgent, height = :height, description = :description WHERE rabbit_id = :rabbit_id")
    suspend fun updateRabbitDatabaseEntity(rabbit_id: String, name: String, breed: String, sex: String, birthday: String, urgent: String, height: String, description: String)

    suspend fun upsertRabbitDatabaseEntity(rabbitDatabaseEntity: RabbitDatabaseEntity) {
        if(getSingleRabbitWithImages(rabbitDatabaseEntity.rabbit_id).rabbit.rabbit_id == rabbitDatabaseEntity.rabbit_id) {
            updateDogDatabaseEntity(
                rabbitDatabaseEntity.rabbit_id,
                rabbitDatabaseEntity.name,
                rabbitDatabaseEntity.breed,
                rabbitDatabaseEntity.sex,
                rabbitDatabaseEntity.birthday,
                rabbitDatabaseEntity.urgent,
                rabbitDatabaseEntity.height,
                rabbitDatabaseEntity.description
            )
        } else {
            insertRabbitDatabaseEntity(rabbitDatabaseEntity)
        }
    }

    @Query("UPDATE Rabbit SET isFavourite = NOT isFavourite WHERE rabbit_id = :rabbit_id")
    suspend fun updateIsFavouriteRabbitDatabaseEntity(rabbit_id: String)

    @Transaction
    @Query("SELECT * FROM Rabbit")
    suspend fun getRabbitsWithImages(): List<RabbitWithImages>
    //endregion

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertImageDatabaseEntity(imageDatabaseEntity: ImageDatabaseEntity): Long
}