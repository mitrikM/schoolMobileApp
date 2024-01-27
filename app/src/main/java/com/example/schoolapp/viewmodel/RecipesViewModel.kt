package com.example.schoolapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.schoolapp.model.RecipesModel
import com.example.schoolapp.repositories.RecipesRepository

class RecipesViewModel(repository: RecipesRepository): ViewModel() {
    val recipes: LiveData<List<RecipesModel>> = repository.getRecipes()


}