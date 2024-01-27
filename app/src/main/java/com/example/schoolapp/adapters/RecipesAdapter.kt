package com.example.schoolapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolapp.R
import com.example.schoolapp.model.RecipesModel

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    private var recipes: List<RecipesModel> = listOf()

    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvPreparationTime: TextView = view.findViewById(R.id.tvPreparationTime)
        // Initialize other views here if necessary
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.tvTitle.text = recipe.title
        holder.tvPreparationTime.text = "Preparation time: ${recipe.preparationTime.toString()} minutes"
        // Set other views here if necessary
    }

    override fun getItemCount() = recipes.size

    fun setData(newRecipes: List<RecipesModel>) {
        Log.d("RecipesAdapter", "New data received: ${newRecipes.size} items.")
        recipes = newRecipes
        notifyDataSetChanged()
    }
}
