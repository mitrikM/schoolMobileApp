package com.example.schoolapp.adapters

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolapp.MainActivity
import com.example.schoolapp.R
import com.example.schoolapp.fragments.RecipeDetailFragment
import com.example.schoolapp.model.RecipesModel

class RecipesAdapter(private val clickListener: (String) -> Unit) : RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    private var recipes: List<RecipesModel> = listOf()

    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvPreparationTime: TextView = view.findViewById(R.id.tvPreparationTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.tvTitle.text = recipe.title
        holder.tvPreparationTime.text = "Preparation time: ${recipe.preparationTime.toString()} minutes"
        holder.itemView.setOnClickListener {
            addToHistory(holder.itemView.context, recipe.title)

            val fragment = RecipeDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("RECIPE_SLUG", recipe.slug)
                }
            }
            (holder.itemView.context as MainActivity).replaceFragment(fragment)
        }

    }

    override fun getItemCount() = recipes.size

    fun setData(newRecipes: List<RecipesModel>) {
        Log.d("RecipesAdapter", "New data received: ${newRecipes.size} items.")
        recipes = newRecipes
        notifyDataSetChanged()
    }

    private fun addToHistory(context: Context, recipeTitle: String) {
        val sharedPref = context.getSharedPreferences("RecipeHistory", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            val historySet = sharedPref.getStringSet("HISTORY", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

            historySet.add(recipeTitle)

            putStringSet("HISTORY", historySet.toSet())
            apply()
        }
    }

}
