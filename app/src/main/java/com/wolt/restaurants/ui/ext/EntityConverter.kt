package com.wolt.restaurants.ui.ext

import com.wolt.restaurants.domain.entities.RestaurantEntity
import com.wolt.restaurants.ui.main.data.RestaurantViewData

fun List<RestaurantEntity>.toRestaurantViewDataList() = this.map {
    RestaurantViewData(
        id = it.venue.id,
        name = it.venue.name,
        description = it.venue.shortDescription,
        imageUrl = it.image.url,
        fav = it.fav
    )
}
