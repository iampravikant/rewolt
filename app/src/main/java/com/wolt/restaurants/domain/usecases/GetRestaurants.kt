package com.wolt.restaurants.domain.usecases

import com.wolt.restaurants.domain.entities.RestaurantEntity
import com.wolt.restaurants.domain.repositories.RestaurantRepo
import javax.inject.Inject

class GetRestaurants @Inject constructor(
    private val repo: RestaurantRepo,
) : UseCase<GetRestaurants.Request, List<RestaurantEntity>>() {

    override suspend fun perform(request: Request) = repo.get(request.lat, request.lon)

    class Request(
        val lat: Double,
        val lon: Double
    ) : UseCase.Request()
}
