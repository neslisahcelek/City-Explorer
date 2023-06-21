package com.example.explorethecity.ui.placelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.explorethecity.R
import com.example.explorethecity.model.Event

class PlaceAdapter(var places:ArrayList<Event>): RecyclerView.Adapter<PlaceViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): PlaceViewHolder {
        val layoutView: View = LayoutInflater.from(parent.context).
        inflate(R.layout.grid_view_place, parent, false)

        return PlaceViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        var currentPlace = places[position]
    }

    override fun getItemCount(): Int {
        return places.size
    }

}
class PlaceViewHolder(view: View): RecyclerView.ViewHolder(view){
    var placeTitle: TextView = view.findViewById(R.id.placeTextView)
    var card: View = view.findViewById(R.id.placeCard)
}