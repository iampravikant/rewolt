package com.wolt.restaurants.ui.ext

import com.wolt.restaurants.domain.entities.RestaurantEntity
import com.wolt.restaurants.ui.main.data.RestaurantViewData
import junit.framework.TestCase
import org.junit.Test

class EntityConverterTest : TestCase() {

    @Test
    fun testSetVisible() {
        val restaurantEntityList = arrayListOf<RestaurantEntity>().apply {
            add(
                RestaurantEntity(
                    RestaurantEntity.Image("url1"),
                    RestaurantEntity.Venue("id1", "name1", "desc1"),
                    false
                )
            )
            add(
                RestaurantEntity(
                    RestaurantEntity.Image("url2"),
                    RestaurantEntity.Venue("id2", "name2", "desc2"),
                    true
                )
            )
        }

        val restaurantViewDataList = arrayListOf<RestaurantViewData>().apply {
            add(RestaurantViewData("id1", "name1", "desc1", "url1", false))
            add(RestaurantViewData("id2", "name2", "desc2", "url2", true))
        }

        assertEquals(restaurantEntityList.toRestaurantViewDataList(), restaurantViewDataList)
    }
}
