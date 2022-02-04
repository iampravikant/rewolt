package com.wolt.restaurants.data.repositories

import com.wolt.restaurants.data.db.RestaurantDao
import com.wolt.restaurants.data.db.entities.RestaurantData
import com.wolt.restaurants.data.remote.RestaurantRemote
import com.wolt.restaurants.domain.entities.RestaurantEntity
import com.wolt.restaurants.domain.repositories.RestaurantRepo

class RestaurantRepoImpl(
    private val remote: RestaurantRemote,
    private val dao: RestaurantDao
) : RestaurantRepo {

    override suspend fun get(lat: Double, lon: Double): List<RestaurantEntity> {
        val response = remote.get(lat, lon)

        if (response.sections.isNullOrEmpty()) return emptyList()

        val entityList = response.sections[0].items ?: return emptyList()
        val topEntityList = entityList.takeIf { it.size < 15 } ?: entityList.subList(0, 15) //Max 15

        val ids = topEntityList.map { it.venue.id }
        val existingDataList = dao.get(ids)
        val existingDataMap = existingDataList.associate { it.id to it.fav }

        topEntityList.forEach { entity ->
            existingDataMap[entity.venue.id]?.let { fav ->
                entity.fav = fav
            }
        }

        val dataList = topEntityList.map { RestaurantData(it.venue.id) }

        dao.insertAll(dataList)

        return topEntityList
    }

    override suspend fun update(id: String, fav: Boolean) = dao.update(RestaurantData(id, fav))
}
