package com.example.schoolapp.repositories

import android.util.Log
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
                    Log.d("RecipesRepository", "Fetching error: ${response.errorBody()?.string()}")
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
}
