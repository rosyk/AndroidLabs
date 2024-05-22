package com.example.lab9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab9.Entities.Place

class PlaceAdapter(
    private val places: List<Place>,
    private val onItemClicked: (Place) -> Unit
) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    class PlaceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.placeName)
        val descriptionTextView: TextView = view.findViewById(R.id.placeDescription)
        val coordinatesTextView: TextView = view.findViewById(R.id.placeCoordinates)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = places[position]
        holder.nameTextView.text = place.name
        holder.descriptionTextView.text = place.description
        holder.coordinatesTextView.text = "Lat: ${place.latitude}, Lon: ${place.longitude}"

        holder.itemView.setOnClickListener {
            onItemClicked(place)
        }
    }

    override fun getItemCount(): Int = places.size
}
