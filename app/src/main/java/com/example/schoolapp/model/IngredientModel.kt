package com.example.schoolapp.model

data class IngredientModel(
    val _id: String,
    val isGroup: Boolean,
    val amountUnit: String,
    val amount: Int,
    val name: String
)
