package com.example.citylistwithrecyclerview.ui.listcities


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.citylistwithrecyclerview.R

class MainAdapter() :
    RecyclerView.Adapter<MainAdapter.CustomViewHolder>() {

    var listCities: List<String> = arrayListOf()
    var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_of_cities, parent, false)
        return CustomViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val city = listCities[position]

        tracker?.let {
            holder.bind(city, it.isSelected(position.toLong()))
        }
    }

    override fun getItemCount(): Int = listCities.count()

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var cityName: TextView = view.findViewById(R.id.tvShowCities)

        fun bind(value: String, isActivated: Boolean = false) {
            cityName.text = value
            itemView.isActivated = isActivated
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {

                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): Long = itemId
            }
    }
}
