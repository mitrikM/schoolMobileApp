package com.example.schoolapp.model

data class RecipesModel(
    val _id: String,
    val title: String,
    val preparationTime: Int?,
    val slug: String,
    val lastModifiedDate: String,
    val sideDish: String?
)