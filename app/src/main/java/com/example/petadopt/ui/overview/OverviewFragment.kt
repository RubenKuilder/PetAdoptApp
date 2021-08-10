package com.example.petadopt.ui.overview

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import com.example.petadopt.data.domain.Animal
import com.example.petadopt.databinding.FragmentFirstBinding
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
class OverviewFragment : Fragment(), CoroutineScope {

    private var _binding: FragmentFirstBinding? = null

    private val viewModel: OverviewViewModel by viewModels()
    private val headerAdapter = OverviewHeaderAdapter()
    private val listAdapter = OverviewListAdapter()

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
        val concatAdapter = ConcatAdapter(headerAdapter, listAdapter)
        binding.overviewList.adapter = concatAdapter

        binding.swipeContainer.setOnRefreshListener {
            Toast.makeText(this.context, "Refresh!", Toast.LENGTH_LONG).show()

            // TODO: Remove timer and add proper check for when data is refreshed or failed to refresh
            Timer().schedule(2000) {
                binding.swipeContainer.isRefreshing = false
            }
        }
        appendAnimalNames()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        })
    }
}