package com.example.petadopt.ui.overview

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.petadopt.R
import com.example.petadopt.data.domain.Animal
import com.example.petadopt.data.domain.Animals
import com.example.petadopt.databinding.FragmentFirstBinding
import com.example.petadopt.utilities.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule
import kotlin.coroutines.CoroutineContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class OverviewFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding
        get() = _binding!!

    private val viewModel: OverviewViewModel by viewModels()
    private val headerAdapter = OverviewHeaderAdapter()
    private lateinit var listAdapter: OverviewListAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)


        listAdapter = OverviewListAdapter(
            AnimalListener { animalIdTypePair ->
                viewModel.onAnimalClicked(animalIdTypePair)
            },
            FavouriteListener { animal ->
                viewModel.addFavourite(animal)
            }
        )
        val concatAdapter = ConcatAdapter(headerAdapter, listAdapter)
        binding.overviewList.adapter = concatAdapter

        dataStateObserver()
        navigationObserver()
        viewModel.setStateEvent(MainStateEvent.GetAnimalsEvents)

        binding.swipeContainer.setOnRefreshListener {
            viewModel.setStateEvent(MainStateEvent.HardRefreshAnimalsEvents)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun dataStateObserver() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState) {
                is DataState.Success<Animals> -> {
                    displayProgressBar(false)
                    binding.swipeContainer.isRefreshing = false

                    appendAnimals(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    binding.swipeContainer.isRefreshing = false

                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun navigationObserver() {
        viewModel.navigateToAnimalDetails.observe(viewLifecycleOwner, Observer { animal ->
            animal?.let {
                val action = OverviewFragmentDirections.actionFirstFragmentToAnimalDetailFragment(animal.first, animal.second)
                this.findNavController().navigate(action)

                viewModel.onAnimalDetailsNavigated()
            }
        })
    }

    private fun displayError(message: String?) {
        if (message != null) {
            Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this.context, "Unkown error", Toast.LENGTH_LONG).show()
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        binding.progressBar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    private fun appendAnimals(animals: Animals) {
        val animalList: MutableList<Animal> = mutableListOf()
        for(dog in animals.dogs) {
            animalList.add(dog)
        }
        for(cat in animals.cats) {
            animalList.add(cat)
        }
        for(rabbit in animals.rabbits) {
            animalList.add(rabbit)
        }

        listAdapter.submitList(animalList)
    }
}