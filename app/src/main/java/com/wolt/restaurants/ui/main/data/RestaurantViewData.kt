package com.wolt.restaurants.ui.main.data

data class RestaurantViewData(
    val id: String,
    val name: String,
    val description: String?,
    val imageUrl: String,
    val fav: Boolean
)
