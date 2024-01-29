package com.example.schoolapp.fragments

import RecipesViewModelFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolapp.R
import com.example.schoolapp.adapters.RecipesAdapter
import com.example.schoolapp.repositories.RecipesRepository
import com.example.schoolapp.viewmodel.RecipesViewModel

class RecipeListFragment : Fragment() {

    private lateinit var viewModel: RecipesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }
    private fun navigateToRecipeDetail(recipeSlug: String) {
        val recipeDetailFragment = RecipeDetailFragment().apply {
            arguments = Bundle().apply {
                putString("RECIPE_SLUG", recipeSlug)
            }
        }

        // Perform the fragment transaction to replace the current fragment with recipeDetailFragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, recipeDetailFragment)
            .addToBackStack(null) // to allow user to navigate back
            .commit()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipesRepository = RecipesRepository()
        val viewModelFactory = RecipesViewModelFactory(recipesRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[RecipesViewModel::class.java]

        val rvRecipes = view.findViewById<RecyclerView>(R.id.rvRecipes)
        val adapter = RecipesAdapter { recipeSlug ->
            navigateToRecipeDetail(recipeSlug)
        }
        rvRecipes.adapter = adapter
        rvRecipes.layoutManager = LinearLayoutManager(context)
        viewModel.recipes.observe(viewLifecycleOwner) { recipes ->

            // Update the adapter's data
            if (recipes != null) {
                // Check if the list is empty
                if (recipes.isEmpty()) {
                    Log.d("RecipeListFragment", "Recipes list is empty")
                } else {
                    Log.d("RecipeListFragment", "Recipes list has data")
                }

                // Update the adapter's data
                adapter.setData(recipes)
            } else {
                Log.d("RecipeListFragment", "Recipes list is null")
            }
        }
    }
}
