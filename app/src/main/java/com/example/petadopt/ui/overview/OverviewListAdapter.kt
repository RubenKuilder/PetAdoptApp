package com.example.petadopt.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petadopt.R
import com.example.petadopt.data.domain.Animal
import com.example.petadopt.data.domain.Animal.Companion.TYPE_CAT
import com.example.petadopt.data.domain.Animal.Companion.TYPE_DOG
import com.example.petadopt.data.domain.Animal.Companion.TYPE_RABBIT
import com.example.petadopt.data.domain.Cat
import com.example.petadopt.data.domain.Dog
import com.example.petadopt.data.domain.Rabbit
import com.example.petadopt.databinding.OverviewListItemBinding
import com.example.petadopt.utilities.Utils
import com.squareup.picasso.Picasso


class OverviewListAdapter: RecyclerView.Adapter<OverviewListAdapter.ViewHolder>() {
    var data = listOf<Animal>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        when(item.type) {
            TYPE_DOG -> holder.bind(item as Dog)
            TYPE_CAT -> holder.bind(item as Cat)
            TYPE_RABBIT -> holder.bind(item as Rabbit)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(val binding: OverviewListItemBinding): RecyclerView.ViewHolder(binding.root) {
        //TODO: Multiple viewholders?
        // List item margins
        // Different margin at top and bottom

        fun bind(item: Dog) {
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

            sb.setLength(0)
            sb.append(item.height + "cm schofthoogte")
            binding.heightTextView.text = sb
        }

        fun bind(item: Cat) {
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

            sb.setLength(0)
            sb.append(item.height + "cm schofthoogte")
            binding.heightTextView.text = sb
        }

        fun bind(item: Rabbit) {
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

            sb.setLength(0)
            sb.append(item.height + "cm")
            binding.heightTextView.text = sb
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = OverviewListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}