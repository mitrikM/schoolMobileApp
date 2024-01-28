package com.example.schoolapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schoolapp.model.RecipeDetailModel
import com.example.schoolapp.model.RecipesModel
import com.example.schoolapp.repositories.RecipesRepository

class RecipesViewModel(repository: RecipesRepository): ViewModel() {
    val recipes: LiveData<List<RecipesModel>> = repository.getRecipes()


    private val _recipeDetail = MutableLiveData<RecipeDetailModel>()
    val recipeDetail: LiveData<RecipeDetailModel> get() = _recipeDetail

    fun fetchRecipeDetail(slug: String, repository:RecipesRepository){
        repository.getRecipesDetail(slug).observeForever{ recipeDetail ->
            _recipeDetail.postValue(recipeDetail)
        }
    }
}