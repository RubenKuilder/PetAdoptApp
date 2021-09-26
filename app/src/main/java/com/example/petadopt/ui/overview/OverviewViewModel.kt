package com.example.petadopt.ui.overview

import android.util.Log
import androidx.lifecycle.*
import com.example.petadopt.data.domain.Animal
import com.example.petadopt.data.domain.Animals
import com.example.petadopt.data.repository.animals.AnimalsRepository
import com.example.petadopt.utilities.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val animalsRepository: AnimalsRepository
): ViewModel() {
    private val _dataState: MutableLiveData<DataState<Animals>> = MutableLiveData()
    val dataState: LiveData<DataState<Animals>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when(mainStateEvent) {
                is MainStateEvent.GetAnimalsEvents -> {
                    animalsRepository.getAnimals(false)
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.HardRefreshAnimalsEvents -> {
                    animalsRepository.getAnimals(true)
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.None -> {
                    // Code here
                }
            }
        }
    }

    fun addFavourite(animal: Animal) {
        viewModelScope.launch {
            animalsRepository.updateIsFavourite(animal)
        }
        setStateEvent(MainStateEvent.GetAnimalsEvents)
    }

    private val _navigateToAnimalDetails = MutableLiveData<Pair<String, Int>>()
    val navigateToAnimalDetails
        get() = _navigateToAnimalDetails

    fun onAnimalClicked(animalIdTypePair: Pair<String, Int>) {
        _navigateToAnimalDetails.value = animalIdTypePair
    }

    fun onAnimalDetailsNavigated() {
        _navigateToAnimalDetails.value = null
    }
}

sealed class MainStateEvent {
    object GetAnimalsEvents: MainStateEvent()
    object HardRefreshAnimalsEvents: MainStateEvent()
    object None: MainStateEvent()
}