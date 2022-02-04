package com.wolt.restaurants.domain.usecases

import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * This class acts as a constant location provider.
 * A location is emitted after every 15 seconds from a list of 10 pre-defined locations.
 * */
class LocationProvider @Inject constructor() {

    companion object {
        private val LOCATIONS = arrayListOf<Pair<Double, Double>>().apply {
            add(Pair(60.170187, 24.930599))
            add(Pair(60.169418, 24.931618))
            add(Pair(60.169818, 24.932906))
            add(Pair(60.170005, 24.935105))
            add(Pair(60.169108, 24.936210))
            add(Pair(60.168355, 24.934869))
            add(Pair(60.167560, 24.932562))
            add(Pair(60.168254, 24.931532))
            add(Pair(60.169012, 24.930341))
            add(Pair(60.170085, 24.929569))
        }
    }

    private var canContinue = true

    fun stop() {
        canContinue = false
    }

    suspend fun fetch(onLocationReceived: (location: Pair<Double, Double>) -> Unit) {
        var position = 0

        while (canContinue) {
            val location = LOCATIONS[position % LOCATIONS.size]
            onLocationReceived.invoke(location)

            delay(15000)

            position++
        }
    }
}
