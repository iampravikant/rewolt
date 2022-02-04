package com.wolt.restaurants.ui.main

import com.wolt.restaurants.domain.entities.RestaurantEntity
import com.wolt.restaurants.domain.repositories.RestaurantRepo
import com.wolt.restaurants.domain.usecases.GetRestaurants
import com.wolt.restaurants.domain.usecases.LocationProvider
import com.wolt.restaurants.domain.usecases.UpdateRestaurant
import com.wolt.restaurants.ui.MainCoroutineRule
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest : TestCase() {

    private val repo = mock(RestaurantRepo::class.java)
    private val locationProvider = LocationProvider()

    private lateinit var getRestaurants: GetRestaurants
    private lateinit var updateRestaurant: UpdateRestaurant

    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantExecutorRule = MainCoroutineRule()

    @Before
    fun setup() = runTest {
        getRestaurants = GetRestaurants(repo)
        updateRestaurant = UpdateRestaurant(repo)

        `when`(repo.get(anyDouble(), anyDouble())).thenReturn(getTestRestaurantEntityList())

        viewModel = MainViewModel(locationProvider, getRestaurants, updateRestaurant)

        locationProvider.stop()
    }

    @Test
    fun init() = runTest {
        assertEquals(viewModel.getRestaurants().value?.size, 2)
    }

    @Test
    fun testOnFavCheckedChanged() = runTest {
        viewModel.onFavCheckedChanged("id", true)

        verify(repo).update("id", true)
    }

    private fun getTestRestaurantEntityList() = arrayListOf<RestaurantEntity>().apply {
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
}
