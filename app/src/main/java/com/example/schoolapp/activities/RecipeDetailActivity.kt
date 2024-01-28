package com.example.schoolapp.activities

import RecipesViewModelFactory
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolapp.R
import com.example.schoolapp.adapters.IngredientsAdapter
import com.example.schoolapp.model.RecipeDetailModel
import com.example.schoolapp.repositories.RecipesRepository
import com.example.schoolapp.viewmodel.RecipesViewModel

class RecipeDetailActivity: AppCompatActivity() {
    private lateinit var viewModel: RecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        val recipesRepository = RecipesRepository()
        val viewModelFactory = RecipesViewModelFactory(recipesRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RecipesViewModel::class.java)

        val slug = intent.getStringExtra("RECIPE_SLUG") ?: return // Return if slug is null

        viewModel.fetchRecipeDetail(slug, recipesRepository)
        viewModel.recipeDetail.observe(this, Observer { recipeDetail ->
            updateUI(recipeDetail)
        })
    }

    private fun updateUI(recipeDetailModel: RecipeDetailModel){
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvPreparationTime = findViewById<TextView>(R.id.tvPreparationTime)
        val tvSideDish = findViewById<TextView>(R.id.tvSideDish)
        val tvServingCount = findViewById<TextView>(R.id.tvServingCount)
        val tvDirections = findViewById<TextView>(R.id.tvDirections)
        val rvIngredients = findViewById<RecyclerView>(R.id.rvIngredients)

        tvTitle.text = recipeDetailModel.title
        tvPreparationTime.text = getString(R.string.preparation_time_template, recipeDetailModel.preparationTime?.toString() ?: "N/A")
        tvSideDish.text = getString(R.string.side_dish_template, recipeDetailModel.sideDish ?: "N/A")
        tvServingCount.text = getString(R.string.serving_count_template, recipeDetailModel.servingCount ?: "N/A")
        tvDirections.text = recipeDetailModel.directions ?: "No directions available."

        recipeDetailModel.ingredients?.let { ingredients ->
            val ingredientsAdapter = IngredientsAdapter(ingredients)
            val rvIngredients = findViewById<RecyclerView>(R.id.rvIngredients)
            rvIngredients.layoutManager = LinearLayoutManager(this)
            rvIngredients.adapter = ingredientsAdapter
        }
    }
}