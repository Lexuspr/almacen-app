package com.example.buses.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.buses.databinding.ListItemPassengerBinding
import com.example.buses.model.Passenger

class PassengerListAdapter(
    private val clickListener: (Passenger) -> Unit
): ListAdapter<Passenger, PassengerListAdapter.PassengerViewHolder>(DiffCallback) {

    class PassengerViewHolder(
        private val binding: ListItemPassengerBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Passenger) {
            binding.passenger = item
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Passenger>() {
        override fun areItemsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PassengerViewHolder(ListItemPassengerBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: PassengerViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener { clickListener(item) }
        holder.bind(item)
    }


}