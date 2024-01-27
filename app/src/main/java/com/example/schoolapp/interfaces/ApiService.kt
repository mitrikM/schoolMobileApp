package com.example.schoolapp.interfaces

import com.example.schoolapp.model.RecipesModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/recipes")
    fun getRecipes(): Call<List<RecipesModel>>
}