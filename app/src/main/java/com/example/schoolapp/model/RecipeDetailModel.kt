package com.example.schoolapp.model

data class RecipeDetailModel(
    val _id: String,
    val title: String,
    val preparationTime: Int?,
    val directions: String?,
    val slug: String,
    val ingredients: List<IngredientModel>?,
    val lastModifiedDate: String,
    val sideDish: String?,
    val servingCount: String?,
    val __v: Int
)
