package com.example.explorethecity.ui.eventlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.explorethecity.R
import com.example.explorethecity.databinding.GridViewEventBinding
import com.example.explorethecity.model.Event

class EventAdapter : ListAdapter<Event,
        EventViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventViewHolder {
        return EventViewHolder(
            GridViewEventBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val currentEvent = getItem(position)
        holder.bind(currentEvent)
    }
    companion object DiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.name.toString() == newItem.name.toString()
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return false
        }
    }
}

class EventViewHolder(private var binding: GridViewEventBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(event:Event) {
        binding.event=event
        binding.executePendingBindings()
    }
}
