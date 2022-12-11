package com.example.buses.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.buses.databinding.ListItemBusBinding
import com.example.buses.model.Bus

class BusesListAdapter(
    private val clickListener: (Bus) -> Unit
): ListAdapter<Bus, BusesListAdapter.BusesViewHolder>(DiffCallback) {

    class BusesViewHolder(
        private val binding: ListItemBusBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Bus) {
            binding.bus = item
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Bus>() {
        override fun areItemsTheSame(oldItem: Bus, newItem: Bus): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Bus, newItem: Bus): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BusesViewHolder(ListItemBusBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: BusesViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener { clickListener(item) }
        holder.bind(item)
    }


}