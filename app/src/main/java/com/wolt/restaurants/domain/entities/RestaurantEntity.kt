package com.wolt.restaurants.domain.entities

import androidx.room.Ignore
import com.google.gson.annotations.SerializedName

data class RestaurantEntity(
    val image: Image,
    val venue: Venue,
    @Ignore var fav: Boolean
) {
    data class Image(
        val url: String
    )

    data class Venue(
        val id: String,
        val name: String,
        @SerializedName("short_description") val shortDescription: String?
    )
}
