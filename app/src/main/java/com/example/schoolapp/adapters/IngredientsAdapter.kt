package com.example.schoolapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolapp.R
import com.example.schoolapp.model.IngredientModel

class IngredientsAdapter(private val ingredients: List<IngredientModel>) : RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder>() {

    class IngredientViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvIngredientName: TextView = view.findViewById(R.id.tvIngredientName)
        val tvIngredientAmount: TextView = view.findViewById(R.id.tvIngredientAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.tvIngredientName.text = ingredient.name
        holder.tvIngredientAmount.text = "${ingredient.amount} ${ingredient.amountUnit}"

    }

    override fun getItemCount() = ingredients.size
}
