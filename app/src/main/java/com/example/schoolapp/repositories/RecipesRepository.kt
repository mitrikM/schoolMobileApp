package com.example.schoolapp.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.schoolapp.model.RecipeDetailModel
import com.example.schoolapp.model.RecipesModel
import com.example.schoolapp.model.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipesRepository {
    private val apiService = RetrofitClient.apiService

    fun getRecipes(): LiveData<List<RecipesModel>>{
        val data = MutableLiveData<List<RecipesModel>>()
        apiService.getRecipes().enqueue(object : Callback<List<RecipesModel>> {
            override fun onResponse(call: Call<List<RecipesModel>?>, response: Response<List<RecipesModel>?>) {
                if (response.isSuccessful) {
                    data.postValue(response.body())
                    Log.d("RecipesRepository", "Fetching successful: ${response.errorBody()?.string()}")
                }
                else{
                    Log.d("RecipesRepository", "Fetching error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<RecipesModel>>, t: Throwable) {
                Log.e("RecipesRepository", "Error fetching recipes: ${t.message}")
            }
        })
        return data
    }

    fun getRecipesDetail(slug: String): MutableLiveData<RecipeDetailModel> {
        val data = MutableLiveData<RecipeDetailModel>()
        apiService.getRecipeDetail(slug).enqueue(object: Callback<RecipeDetailModel> {
            override fun onResponse(
                call: Call<RecipeDetailModel>,
                response: Response<RecipeDetailModel>
            ) {
                if (response.isSuccessful){
                    data.postValue(response.body())
                    Log.d("RecipesRepository", "Fetching detail successful: ${response.errorBody()?.string()}")
                }
                else{
                    Log.e("RecipesRepository", "Error fetching recipes detail: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<RecipeDetailModel>, t: Throwable) {
                Log.e("RecipesRepository", "Error fetching recipes: ${t.message}")
            }

        })
        return data

    }
}
