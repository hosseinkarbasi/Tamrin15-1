package com.example.citylistwithrecyclerview.ui.listcities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.citylistwithrecyclerview.R
import com.example.citylistwithrecyclerview.databinding.ListOfCitiesBinding
import com.example.citylistwithrecyclerview.ui.MainActivity

class ListOfCitiesFragment : Fragment(R.layout.list_of_cities) {

    private lateinit var binding: ListOfCitiesBinding
    private lateinit var rv: RecyclerView
    private var adapter = MainAdapter()
    private val viewModel: ListOfCitiesViewModel by activityViewModels()
    private var listOfCities = mutableListOf<String>()
    private lateinit var tracker: SelectionTracker<Long>

    private val navController by lazy { findNavController() }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ListOfCitiesBinding.bind(view)
        rv = view.findViewById(R.id.recyclerViewListOfCities)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter
        adapter.listCities = listOfCities


        viewModel.listOfCities.observe(viewLifecycleOwner) {
            listOfCities.clear()
            listOfCities.addAll(it)
            adapter.notifyDataSetChanged()
        }

        tracker = viewModel.tracker(rv, adapter)

        binding.btnShowSelectedItem.setOnClickListener {
            navController.navigate(ListOfCitiesFragmentDirections.actionListOfCitiesFragmentToShowSelectedCitiesFragment())
        }


//        tracker.onRestoreInstanceState(savedInstanceState)


//        tracker = SelectionTracker.Builder(
//            "mySelection",
//            rv,
//            MyItemKeyProvider(rv),
//            MyLookup(rv),
//            StorageStrategy.createLongStorage()
//        ).withSelectionPredicate(
//            SelectionPredicates.createSelectAnything()
//        ).build()
//
//        adapter.tracker = tracker
//        tracker.onRestoreInstanceState(savedInstanceState)
//
////        tra.addObserver(
////            object : SelectionTracker.SelectionObserver<Long>() {
////                override fun onSelectionChanged() {
////                    super.onSelectionChanged()
////                    val items = tracker?.selection!!.size()
////                    val list = tra.selection.map {
////                        Log.d("select", it.toString())
////                    }
////                }
////            })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        tracker.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        tracker.onRestoreInstanceState(savedInstanceState)
        super.onViewStateRestored(savedInstanceState)
    }
}
