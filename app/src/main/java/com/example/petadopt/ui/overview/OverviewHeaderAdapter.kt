package com.example.petadopt.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petadopt.databinding.OverviewHeaderItemBinding
import com.example.petadopt.databinding.OverviewListItemBinding

class OverviewHeaderAdapter: RecyclerView.Adapter<OverviewHeaderItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OverviewHeaderItemViewHolder {
        return OverviewHeaderItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: OverviewHeaderItemViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}

class OverviewHeaderItemViewHolder(val binding: OverviewHeaderItemBinding): RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): OverviewHeaderItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = OverviewHeaderItemBinding.inflate(layoutInflater, parent, false)
            return OverviewHeaderItemViewHolder(binding)
        }
    }
}
