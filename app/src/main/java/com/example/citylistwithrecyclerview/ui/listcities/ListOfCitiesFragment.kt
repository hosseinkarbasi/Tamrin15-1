package com.example.citylistwithrecyclerview.ui.listcities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.citylistwithrecyclerview.R
import com.example.citylistwithrecyclerview.databinding.ListOfCitiesBinding

class ListOfCitiesFragment : Fragment(R.layout.list_of_cities) {

    private lateinit var binding: ListOfCitiesBinding
    private lateinit var rv: RecyclerView
    private var adapter = MainAdapter()
    private val viewModel: ListOfCitiesViewModel by activityViewModels()
    private lateinit var tracker: SelectionTracker<Long>
    private val navController by lazy { findNavController() }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ListOfCitiesBinding.bind(view)
        rv = view.findViewById(R.id.recyclerViewListOfCities)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter
        adapter.listCities = listOf(
            "Tehran", "London", "Tokyo", "Paris", "Rome",
            "Berlin", "Barcelona", "Zagreb", "Helsinki", "AbdolCity"
        )

        tracker = viewModel.tracker(rv, adapter)

        if (savedInstanceState != null)
            tracker.onRestoreInstanceState(savedInstanceState)

        binding.btnShowSelectedItem.setOnClickListener {
            navController.navigate(ListOfCitiesFragmentDirections.actionListOfCitiesFragmentToShowSelectedCitiesFragment())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        tracker.onSaveInstanceState(outState)
    }
}
