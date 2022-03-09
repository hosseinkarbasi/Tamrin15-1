package com.example.citylistwithrecyclerview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.citylistwithrecyclerview.R
import com.example.citylistwithrecyclerview.databinding.ActivityMainBinding
import com.example.citylistwithrecyclerview.ui.listcities.ListOfCitiesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val navController by lazy {
        findNavController(R.id.container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }
}