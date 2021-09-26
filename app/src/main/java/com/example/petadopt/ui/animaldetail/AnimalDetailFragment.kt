package com.example.petadopt.ui.animaldetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.petadopt.data.domain.Animal
import com.example.petadopt.data.domain.Animals
import com.example.petadopt.databinding.AnimalDetailFragmentBinding
import com.example.petadopt.databinding.FragmentFirstBinding
import com.example.petadopt.utilities.DataState
import com.example.petadopt.utilities.Utils
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimalDetailFragment : Fragment() {
    private var _binding: AnimalDetailFragmentBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding
        get() = _binding!!

    private val viewModel: AnimalDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AnimalDetailFragmentBinding.inflate(inflater, container, false)

        val arguments = AnimalDetailFragmentArgs.fromBundle(requireArguments())

        dataStateObserver()
        viewModel.setStateEvent(MainStateEvent.GetAnimalEvents, arguments.animalId, arguments.animalType)

        return binding.root
    }

    private fun dataStateObserver() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<Animal> -> {
                    displayProgressBar(false)

                    appendAnimal(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)

                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
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
        Log.i("Debug", "$isDisplayed")
        binding.progressBar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    private fun appendAnimal(animal: Animal) {
        binding.name.text = animal.name
        binding.breed.text = animal.breed
        binding.sex.text = animal.sex

        val sb = StringBuilder()
        if(Utils().dateMonthDiff(animal.birthday) / 12 > 0) {
            sb.append(Utils().dateMonthDiff(animal.birthday) / 12)
            sb.append(" jaar")
        } else {
            sb.append(Utils().dateMonthDiff(animal.birthday))
            sb.append(" maand")
        }
        binding.age.text = sb.toString()

        binding.urgent.text = animal.urgent
        binding.height.text = animal.height
        binding.description.text = animal.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}