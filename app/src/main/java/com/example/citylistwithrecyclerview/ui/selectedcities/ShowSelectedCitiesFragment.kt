package com.example.citylistwithrecyclerview.ui.selectedcities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.citylistwithrecyclerview.R
import com.example.citylistwithrecyclerview.ui.listcities.ListOfCitiesViewModel

class ShowSelectedCitiesFragment : Fragment(R.layout.show_selected_cities) {

    private val adapter = SelectedItemAdapter()
    private lateinit var rv: RecyclerView
    private val viewModel: ListOfCitiesViewModel by activityViewModels()
    private var listOfCitiesSelected = mutableListOf<String>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = view.findViewById(R.id.recyclerViewSelectedItem)
        rv.layoutManager = LinearLayoutManager(requireContext())
        adapter.listOfSelected = listOfCitiesSelected
        rv.adapter = adapter

        viewModel.listOfSelectedCities.observe(viewLifecycleOwner) {
            listOfCitiesSelected.clear()
            listOfCitiesSelected.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }
}