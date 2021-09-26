package com.example.petadopt.ui.overview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.petadopt.R
import com.example.petadopt.data.domain.Animal
import com.example.petadopt.data.domain.Cat
import com.example.petadopt.data.domain.Dog
import com.example.petadopt.data.domain.Rabbit
import com.example.petadopt.databinding.OverviewDogListItemBinding
import com.example.petadopt.databinding.OverviewListItemBinding
import com.example.petadopt.utilities.Utils
import com.example.petadopt.utilities.Utils.Companion.TYPE_CAT
import com.example.petadopt.utilities.Utils.Companion.TYPE_DOG
import com.example.petadopt.utilities.Utils.Companion.TYPE_RABBIT
import com.squareup.picasso.Picasso


class OverviewListAdapter(val animalClickListener: AnimalListener, val favouriteClickListener: FavouriteListener): ListAdapter<Animal, RecyclerView.ViewHolder>(OverviewListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            TYPE_DOG -> return OverviewDogListItemViewHolder.from(parent)
        }

        return OverviewListItemViewHolder.from(parent)
    }

    override fun getItemViewType(position: Int): Int {
        when(getItem(position).type) {
            TYPE_DOG -> return TYPE_DOG
            TYPE_CAT -> return TYPE_CAT
            TYPE_RABBIT -> return TYPE_RABBIT
        }

        return -1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when(getItemViewType(position)) {
            TYPE_DOG -> (holder as OverviewDogListItemViewHolder).bind(item as Dog, animalClickListener, favouriteClickListener)
            TYPE_CAT -> (holder as OverviewListItemViewHolder).bindCat(item as Cat, animalClickListener, favouriteClickListener)
            TYPE_RABBIT -> (holder as OverviewListItemViewHolder).bindRabbit(item as Rabbit, animalClickListener, favouriteClickListener)
            -1 -> Log.i("Debug", "No valid ViewType")
        }
    }

    class OverviewListItemViewHolder(private val binding: OverviewListItemBinding): RecyclerView.ViewHolder(binding.root) {
        //TODO: List item margins
        // Different margin at top and bottom (position 0 and last?)

        fun bindCat(
            item: Cat,
            animalClickListener: AnimalListener,
            favouriteClickListener: FavouriteListener
        ) {
            binding.animal = item
            binding.animalListener = animalClickListener
            binding.favouriteListener = favouriteClickListener

            Picasso.get()
                .load(item.images[0].url)
                .placeholder(R.drawable.ic_baseline_pets_24)
                .into(binding.previewImage)
            binding.nameTextView.text = item.name
            binding.breedTextView.text = item.breed

            val sb = StringBuilder()
            sb.append(item.sex + ", ")
            if(Utils().dateMonthDiff(item.birthday) / 12 > 0) {
                sb.append(Utils().dateMonthDiff(item.birthday) / 12)
                sb.append(" jaar oud")
            } else {
                sb.append(Utils().dateMonthDiff(item.birthday))
                sb.append(" maanden oud")
            }
            binding.sexAndAgeTextView.text = sb.toString()

            if(item.isFavourite) {
                binding.favouriteImageView.setColorFilter(ContextCompat.getColor(binding.favouriteImageView.context, R.color.red_500), android.graphics.PorterDuff.Mode.SRC_IN);
                binding.favouriteImageView.alpha = 1F
            } else {
                binding.favouriteImageView.setColorFilter(ContextCompat.getColor(binding.favouriteImageView.context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
                binding.favouriteImageView.alpha = 0.25F
            }
        }

        fun bindRabbit(
            item: Rabbit,
            animalClickListener: AnimalListener,
            favouriteClickListener: FavouriteListener
        ) {
            binding.animal = item
            binding.animalListener = animalClickListener
            binding.favouriteListener = favouriteClickListener

            Picasso.get()
                .load(item.images[0].url)
                .placeholder(R.drawable.ic_baseline_pets_24)
                .into(binding.previewImage)
            binding.nameTextView.text = item.name
            binding.breedTextView.text = item.breed

            val sb = StringBuilder()
            sb.append(item.sex + ", ")
            if(Utils().dateMonthDiff(item.birthday) / 12 > 0) {
                sb.append(Utils().dateMonthDiff(item.birthday) / 12)
                sb.append(" jaar oud")
            } else {
                sb.append(Utils().dateMonthDiff(item.birthday))
                sb.append(" maanden oud")
            }
            binding.sexAndAgeTextView.text = sb.toString()

            if(item.isFavourite) {
                binding.favouriteImageView.setColorFilter(ContextCompat.getColor(binding.favouriteImageView.context, R.color.red_500), android.graphics.PorterDuff.Mode.SRC_IN);
                binding.favouriteImageView.alpha = 1F
            } else {
                binding.favouriteImageView.setColorFilter(ContextCompat.getColor(binding.favouriteImageView.context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
                binding.favouriteImageView.alpha = 0.25F
            }
        }

        companion object {
            fun from(parent: ViewGroup): OverviewListItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = OverviewListItemBinding.inflate(layoutInflater, parent, false)
                return OverviewListItemViewHolder(binding)
            }
        }
    }

    class OverviewDogListItemViewHolder(private val binding: OverviewDogListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Dog,
            animalClickListener: AnimalListener,
            favouriteClickListener: FavouriteListener
        ) {
            binding.animal = item
            binding.animalListener = animalClickListener
            binding.favouriteListener = favouriteClickListener

            Picasso.get()
                .load(item.images[0].url)
                .placeholder(R.drawable.ic_baseline_pets_24)
                .into(binding.previewImage)
            binding.nameTextView.text = item.name
            binding.breedTextView.text = item.breed

            val sb = StringBuilder()
            sb.append(item.sex + ", ")
            if(Utils().dateMonthDiff(item.birthday) / 12 > 0) {
                sb.append(Utils().dateMonthDiff(item.birthday) / 12)
                sb.append(" jaar oud")
            } else {
                sb.append(Utils().dateMonthDiff(item.birthday))
                sb.append(" maanden oud")
            }
            binding.sexAndAgeTextView.text = sb.toString()

            if(item.isFavourite) {
                binding.favouriteImageView.setColorFilter(ContextCompat.getColor(binding.favouriteImageView.context, R.color.red_500), android.graphics.PorterDuff.Mode.SRC_IN);
                binding.favouriteImageView.alpha = 1F
            } else {
                binding.favouriteImageView.setColorFilter(ContextCompat.getColor(binding.favouriteImageView.context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
                binding.favouriteImageView.alpha = 0.25F
            }
        }

        companion object {
            fun from(parent: ViewGroup): OverviewDogListItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = OverviewDogListItemBinding.inflate(layoutInflater, parent, false)
                return OverviewDogListItemViewHolder(binding)
            }
        }
    }
}

class OverviewListDiffCallback : DiffUtil.ItemCallback<Animal>() {
    override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
        return newItem.id == oldItem.id &&
                newItem.name == oldItem.name &&
                newItem.breed == oldItem.breed &&
                newItem.sex == oldItem.sex &&
                newItem.birthday == oldItem.birthday &&
                newItem.urgent == oldItem.urgent &&
                newItem.height == oldItem.height &&
                newItem.description == oldItem.description &&
                newItem.images == oldItem.images &&
                newItem.isFavourite == oldItem.isFavourite
    }
}

class AnimalListener(val animalClickListener: (animalIdTypePair: Pair<String, Int>) -> Unit) {
    fun onClick(animalId: String, animalType: Int) {
        val animalIdTypePair = Pair(animalId, animalType)
        animalClickListener(animalIdTypePair)
    }
}

class FavouriteListener(val favouriteClickListener: (animal: Animal) -> Unit) {
    fun onClick(animal: Animal) = favouriteClickListener(animal)
}