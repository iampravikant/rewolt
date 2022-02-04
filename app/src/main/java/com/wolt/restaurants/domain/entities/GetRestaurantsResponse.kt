package com.wolt.restaurants.domain.entities

data class GetRestaurantsResponse(
    val sections: List<Section>
) {
    data class Section(
        val items: List<RestaurantEntity>?
    )
}
