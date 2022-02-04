package com.wolt.restaurants.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RestaurantData(
    @PrimaryKey val id: String,
    val fav: Boolean = false
)
