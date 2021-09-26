package com.example.petadopt.ui.animaldetail

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
class AnimalDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val animalsRepository: AnimalsRepository
): ViewModel() {
    private val _dataState: MutableLiveData<DataState<Animal>> = MutableLiveData()
    val dataState: LiveData<DataState<Animal>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent, id: String, type: Int) {
        viewModelScope.launch {
            when(mainStateEvent) {
                is MainStateEvent.GetAnimalEvents -> {
                    animalsRepository.getSingleAnimal(id, type)
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
}

sealed class MainStateEvent {
    object GetAnimalEvents: MainStateEvent()
    object None: MainStateEvent()
}