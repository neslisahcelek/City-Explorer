package com.example.explorethecity.ui.eventlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.explorethecity.R
import com.example.explorethecity.model.Event

class EventAdapter (var events:ArrayList<Event>): RecyclerView.Adapter<EventViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventViewHolder {
        val layoutView:View = LayoutInflater.from(parent.context).
        inflate(R.layout.card_event, parent, false)

        return EventViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        var currentEvent = events[position]
        holder.eventTitle.text = currentEvent.name
        /*
        holder.imageView

        holder.cardView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val page: Uri = Uri.parse(currentEvent.url)
                val browserIntent: Intent = Intent(Intent.ACTION_VIEW, page)
                startActivity(holder.itemView.context, browserIntent, Bundle())
            }
        })

         */
    }

    override fun getItemCount(): Int {
        return events.size
    }
}

class EventViewHolder(view: View): RecyclerView.ViewHolder(view){
    var eventTitle: TextView = view.findViewById(R.id.eventTextView)
    var cardView: View = view.findViewById(R.id.eventCardView)
}
