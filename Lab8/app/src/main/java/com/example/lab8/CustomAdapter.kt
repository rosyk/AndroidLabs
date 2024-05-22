package com.example.lab8

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CustomAdapter(private val names: List<ItemModel>, private val onItemClick: (position: Int)->Unit): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(private val ItemView: View, onItemClick: (position: Int) -> Unit): RecyclerView.ViewHolder(ItemView) {
        init {
            itemView.setOnClickListener{
                onItemClick(adapterPosition)
            }
        }
        val textView: TextView = itemView.findViewById(R.id.card_text)
        val imageView: ImageView = itemView.findViewById(R.id.card_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ViewHolder(view, onItemClick)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemModel = names[position]
        holder.textView.text = ItemModel.name
        Glide.with(holder.itemView.context)
            .load(ItemModel.image)
//            .apply(RequestOptions().override(600, 20))
            .error(R.drawable.ic_launcher_background)
            .into(holder.imageView)
    }

}