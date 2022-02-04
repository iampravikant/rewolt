package com.wolt.restaurants.domain.repositories

import com.wolt.restaurants.domain.entities.RestaurantEntity

interface RestaurantRepo {
    suspend fun get(lat: Double, lon: Double): List<RestaurantEntity>
    suspend fun update(id: String, fav: Boolean)
}
