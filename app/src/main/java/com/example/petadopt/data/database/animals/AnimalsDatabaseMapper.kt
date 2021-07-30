package com.example.petadopt.data.database.animals

import com.example.petadopt.data.database.animals.entities.DogDatabaseEntity
import com.example.petadopt.data.database.animals.entities.DogWithImages
import com.example.petadopt.data.database.animals.entities.ImageDatabaseEntity
import com.example.petadopt.data.domain.Dog
import com.example.petadopt.utilities.EntityMapper
import javax.inject.Inject

class AnimalsDatabaseMapper
@Inject
constructor() {
    //TODO: WTF moet ik hier doen?
    // Animals mappen of Dog?
    // Hoe moet ik dit doen!?
    // Volgens mij is het het idee dat ik DogDatabaseEntity naar Dog map.
    // Of DogDatabaseEntity en CatDatabaseEntity naar Animals map.
    // Moet ik in plaats van een Animals class gewoon een list maken in de DAO met daarin alle animals zoals Dog en Cat?
    // In de DAO kan dan: SELECT * FROM Dog / SELECT * FROM Cat
    // In de Repository kunnen deze queries dan worden aangeroepen. Dog objects kunnen in een Dog list worden opgeslagen en Cat objects in een Cat list.
    // Vanuit een ViewModel wordt dan de GetAnimals() methode van de Repsository aangeroepen die een list returned van Dogs en Cats (hiervoor moeten Dog en Cat objects waarschijnlijk wel dezelfde interface hebben, namelijk Animal)
    // Dog en Cat classes moeten wel apart zijn. Een class voor alle soorten dieren is niet mooi aangezien er wel duidelijk moet zijn om wat voor dier het gaat.

    //region List from DogWithImages objects to a list of Dog objects
    // Return a list of Dog objects generated from a list of DogWithImages objects (DogWithImages is a database object)
    fun mapDogWithImagesListToDogList(entities: List<DogWithImages>): List<Dog> {
        return entities.map { mapDogWithImagesToDog(it) }
    }

    // Return a Dog object generated from a DogWithImages object (DogWithImages is a database object)
    fun mapDogWithImagesToDog(entity: DogWithImages): Dog {
        var images: MutableList<String> = mutableListOf()

        entity.images.forEach {
            images.add(it.url)
        }

        return Dog(
            id = entity.dog.dog_id,
            name = entity.dog.name,
            breed = entity.dog.breed,
            sex = entity.dog.sex,
            birthday = entity.dog.birthday,
            urgent = entity.dog.urgent,
            height = entity.dog.height,
            description = entity.dog.description,
            images = images
        )
    }
    //endregion

    //region Map Dog and Image Objects to their database object varians
    // Return a DogDatabaseEntity object generated from a Dog object
    fun mapDogToDogDatabaseEntity(domainModel: Dog): DogDatabaseEntity {
        return DogDatabaseEntity(
            dog_id = domainModel.id,
            name = domainModel.name,
            breed = domainModel.breed,
            sex = domainModel.sex,
            birthday = domainModel.birthday,
            urgent = domainModel.urgent,
            height = domainModel.height,
            description = domainModel.description
        )
    }

    // Return a list of ImageDatabaseEntity objects generated from a Dog object
    //TODO: Not sure if this is necessary or even correct
    fun mapImageToImageDatabaseEntity(domainModel: Dog): List<ImageDatabaseEntity> {
        var imageDatabaseEntity: MutableList<ImageDatabaseEntity> = mutableListOf()

        domainModel.images.forEach {
            imageDatabaseEntity.add(ImageDatabaseEntity(domainModel.id, it))
        }

        return imageDatabaseEntity
    }
    //endregion
}