package com.example.petadopt.ui.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.petadopt.R
import com.example.petadopt.data.domain.Dog
import com.example.petadopt.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import kotlin.coroutines.CoroutineContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class OverviewFragment : Fragment(), CoroutineScope {

    private var _binding: FragmentFirstBinding? = null

    val viewModel: OverviewViewModel by viewModels()

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        job = Job()
        appendAnimalNames()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bannerButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    private fun appendAnimalNames() = launch {
        val animals = viewModel.animals.await()

        animals.observe(viewLifecycleOwner, Observer { animals ->
            if(animals == null) return@Observer

            val sb = StringBuilder()
            for(dog in animals.dogs) {
                sb.append(dog.name + " - " + dog.breed + "\n")
            }

            sb.append("\n")
            for(cat in animals.cats) {
                sb.append(cat.name + " - " + cat.breed + "\n")
            }

            sb.append("\n")
            for(rabbit in animals.rabbits) {
                sb.append(rabbit.name + " - " + rabbit.breed + "\n")
            }

            binding.textView.text = sb.toString()
        })
    }
}