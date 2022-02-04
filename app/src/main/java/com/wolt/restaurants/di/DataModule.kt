package com.wolt.restaurants.di

import com.wolt.restaurants.data.db.RestaurantDao
import com.wolt.restaurants.data.remote.RestaurantRemote
import com.wolt.restaurants.data.repositories.RestaurantRepoImpl
import com.wolt.restaurants.domain.repositories.RestaurantRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideRestaurantRepo(
        remote: RestaurantRemote,
        dao: RestaurantDao
    ): RestaurantRepo = RestaurantRepoImpl(remote, dao)
}
