package com.example.schoolapp

import RecipesViewModelFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolapp.adapters.RecipesAdapter
import com.example.schoolapp.repositories.RecipesRepository
import com.example.schoolapp.viewmodel.RecipesViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: RecipesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recipesRepository = RecipesRepository()
        val viewModelFactory = RecipesViewModelFactory(recipesRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[RecipesViewModel::class.java]

        val rvRecipes = findViewById<RecyclerView>(R.id.rvRecipes)
        val adapter = RecipesAdapter()
        rvRecipes.adapter = adapter
        rvRecipes.layoutManager = LinearLayoutManager(this)
        viewModel.recipes.observe(this) { recipes ->
            Log.d("nigger", "Recipes list is empty")

            // Update the adapter's data
            if (recipes != null) {
                // Check if the list is empty
                if (recipes.isEmpty()) {
                    Log.d("MainActivity", "Recipes list is empty")
                } else {
                    Log.d("MainActivity", "Recipes list has data")
                }

                // Update the adapter's data
                adapter.setData(recipes)
            } else {
                Log.d("MainActivity", "Recipes list is null")
            }
        }
    }
}