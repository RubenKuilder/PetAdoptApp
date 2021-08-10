package com.example.petadopt.ui.overview

import android.util.Log
import androidx.lifecycle.*
import com.example.petadopt.data.repository.animals.AnimalsRepository
import com.example.petadopt.internal.lazyDeferred
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val animalsRepository: AnimalsRepository
): ViewModel() {
    val animals by lazyDeferred {
        animalsRepository.getAnimals()
    }
}