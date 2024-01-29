package com.example.schoolapp.fragments

import RecipesViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolapp.R
import com.example.schoolapp.adapters.IngredientsAdapter
import com.example.schoolapp.model.RecipeDetailModel
import com.example.schoolapp.repositories.RecipesRepository
import com.example.schoolapp.viewmodel.RecipesViewModel

class RecipeDetailFragment : Fragment() {
    private lateinit var viewModel: RecipesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipesRepository = RecipesRepository()
        val viewModelFactory = RecipesViewModelFactory(recipesRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RecipesViewModel::class.java)

        val slug = arguments?.getString("RECIPE_SLUG") ?: return

        viewModel.fetchRecipeDetail(slug, recipesRepository)
        viewModel.recipeDetail.observe(viewLifecycleOwner) { recipeDetail ->
            updateUI(view, recipeDetail)
        }
    }

    private fun updateUI(view: View, recipeDetailModel: RecipeDetailModel) {
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvPreparationTime = view.findViewById<TextView>(R.id.tvPreparationTime)
        val tvSideDish = view.findViewById<TextView>(R.id.tvSideDish)
        val tvServingCount = view.findViewById<TextView>(R.id.tvServingCount)
        val tvDirections = view.findViewById<TextView>(R.id.tvDirections)
        val rvIngredients = view.findViewById<RecyclerView>(R.id.rvIngredients)

        tvTitle.text = recipeDetailModel.title
        tvPreparationTime.text = getString(R.string.preparation_time_template, recipeDetailModel.preparationTime?.toString() ?: "N/A")
        tvSideDish.text = getString(R.string.side_dish_template, recipeDetailModel.sideDish ?: "N/A")
        tvServingCount.text = getString(R.string.serving_count_template, recipeDetailModel.servingCount ?: "N/A")
        tvDirections.text = recipeDetailModel.directions ?: "No directions available."

        recipeDetailModel.ingredients?.let { ingredients ->
            val ingredientsAdapter = IngredientsAdapter(ingredients)
            rvIngredients.layoutManager = LinearLayoutManager(context)
            rvIngredients.adapter = ingredientsAdapter
        }
    }
}
