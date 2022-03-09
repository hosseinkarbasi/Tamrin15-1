package com.example.citylistwithrecyclerview.ui.listcities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView
import com.example.citylistwithrecyclerview.ui.MyModel

class ListOfCitiesViewModel : ViewModel() {

    private val _listOfSelectedCities = MutableLiveData<List<String>>()
    val listOfSelectedCities: LiveData<List<String>> = _listOfSelectedCities

    fun tracker(rv: RecyclerView, adapter: MainAdapter): SelectionTracker<Long> {
        val track = SelectionTracker.Builder(
            "mySelection",
            rv,
            MyItemKeyProvider(rv),
            MyLookup(rv),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        track.addObserver(object : SelectionTracker.SelectionObserver<Long>() {
            override fun onSelectionChanged() {
                super.onSelectionChanged()
                _listOfSelectedCities.postValue(track.selection.map {
                    adapter.listCities[it.toInt()]
                }.toList())
            }
        })

        adapter.tracker = track
        return track
    }
}