package com.wolt.restaurants.domain.usecases

import com.wolt.restaurants.domain.repositories.RestaurantRepo
import javax.inject.Inject

class UpdateRestaurant @Inject constructor(
    private val repo: RestaurantRepo
) : UseCase<UpdateRestaurant.Request, Unit>() {

    override suspend fun perform(request: Request) = repo.update(request.id, request.fav)

    class Request(
        val id: String,
        val fav: Boolean
    ) : UseCase.Request()
}
