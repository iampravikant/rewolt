package com.wolt.restaurants.data.remote

import com.wolt.restaurants.domain.entities.GetRestaurantsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantRemote {

    companion object {
        private const val ENDPOINT = "restaurants"
    }

    @GET(ENDPOINT)
    suspend fun get(@Query("lat") lat: Double, @Query("lon") lon: Double): GetRestaurantsResponse
}
