package com.example.citylistwithrecyclerview.ui.listcities

import android.os.Bundle
import android.widget.Adapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView

class ListOfCitiesViewModel : ViewModel() {

    private val _listOfCities = MutableLiveData<List<String>>()
    val listOfCities: LiveData<List<String>> = _listOfCities

    private val _listOfSelectedCities = MutableLiveData<List<String>>()
    val listOfSelectedCities: LiveData<List<String>> = _listOfSelectedCities

    init {
        _listOfCities.postValue(
            listOf(
                "Tehran", "London", "Tokyo", "Paris", "Rome",
                "Berlin", "Barcelona", "Zagreb", "Helsinki", "AbdolCity"
            )
        )
    }

    fun tracker(rv: RecyclerView, adapter: MainAdapter): SelectionTracker<Long> {
        val track = SelectionTracker.Builder(
            "mySelection",
            rv,
            StableIdKeyProvider(rv),
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