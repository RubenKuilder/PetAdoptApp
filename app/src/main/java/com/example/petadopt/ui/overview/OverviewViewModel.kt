package com.example.petadopt.ui.overview

import androidx.lifecycle.*
import com.example.petadopt.data.repository.animals.DogRepository
import com.example.petadopt.internal.lazyDeferred
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel
@Inject
constructor(
    private val dogRepository: DogRepository
): ViewModel() {
    val dogs by lazyDeferred {
        dogRepository.getDogs()
    }
}