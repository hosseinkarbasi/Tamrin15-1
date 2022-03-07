package com.example.citylistwithrecyclerview.ui.selectedcities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.citylistwithrecyclerview.R

class SelectedItemAdapter : RecyclerView.Adapter<SelectedItemAdapter.CustomViewHolder>() {

    var listOfSelected: List<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cellForRow = inflater.inflate(R.layout.item_of_selected_cities, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val city = listOfSelected[position]
        holder.cityName.text = city
    }

    override fun getItemCount(): Int = listOfSelected.count()

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var cityName: TextView = view.findViewById(R.id.tvSelected)
    }
}