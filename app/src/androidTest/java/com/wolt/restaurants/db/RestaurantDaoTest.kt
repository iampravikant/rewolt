package com.wolt.restaurants.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wolt.restaurants.data.db.AppDatabase
import com.wolt.restaurants.data.db.RestaurantDao
import com.wolt.restaurants.data.db.entities.RestaurantData
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RestaurantDaoTest {

    private lateinit var dao: RestaurantDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.restaurantDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList_existing() = runBlocking {
        val restaurantListInsert = arrayListOf<RestaurantData>().apply {
            add(RestaurantData("id1", true))
            add(RestaurantData("id2", false))
        }

        dao.insertAll(restaurantListInsert)

        val restaurantListGet = dao.get(arrayListOf("id1"))

        Assert.assertThat(restaurantListGet[0], equalTo(restaurantListInsert[0]))
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList_non_existing() = runBlocking {
        val restaurantListInsert = arrayListOf<RestaurantData>().apply {
            add(RestaurantData("id1", true))
            add(RestaurantData("id2", false))
        }

        dao.insertAll(restaurantListInsert)

        val restaurantListGet = dao.get(arrayListOf("id3"))

        Assert.assertTrue(restaurantListGet.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun testUpdate_existing() = runBlocking {
        val restaurantListInsert = arrayListOf<RestaurantData>().apply {
            add(RestaurantData("id1", false))
            add(RestaurantData("id2", false))
        }

        dao.insertAll(restaurantListInsert)

        val updateData = RestaurantData("id1", true)

        dao.update(updateData)

        val restaurantListGet = dao.get(arrayListOf("id1"))

        Assert.assertThat(restaurantListGet[0], equalTo(updateData))
    }
}
