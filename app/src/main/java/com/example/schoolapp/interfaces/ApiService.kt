package com.example.schoolapp.interfaces

import com.example.schoolapp.model.RecipeDetailModel
import com.example.schoolapp.model.RecipesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/recipes")
    fun getRecipes(): Call<List<RecipesModel>>

    @GET("api/recipes/{slug}")
    fun getRecipeDetail(@Path("slug") slug: String): Call<RecipeDetailModel>
}