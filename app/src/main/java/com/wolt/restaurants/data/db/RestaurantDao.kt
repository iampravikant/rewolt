package com.wolt.restaurants.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.wolt.restaurants.data.db.entities.RestaurantData

@Dao
interface RestaurantDao {
    @Insert(onConflict = IGNORE)
    suspend fun insertAll(dataList: List<RestaurantData>)

    @Query("SELECT * FROM RestaurantData WHERE id IN (:idList)")
    suspend fun get(idList: List<String>): List<RestaurantData>

    @Update
    suspend fun update(data: RestaurantData)
}
