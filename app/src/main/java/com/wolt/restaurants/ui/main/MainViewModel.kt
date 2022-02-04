package com.wolt.restaurants.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wolt.restaurants.domain.Result
import com.wolt.restaurants.domain.usecases.GetRestaurants
import com.wolt.restaurants.domain.usecases.LocationProvider
import com.wolt.restaurants.domain.usecases.UpdateRestaurant
import com.wolt.restaurants.ui.ext.toRestaurantViewDataList
import com.wolt.restaurants.ui.main.data.RestaurantViewData
import com.wolt.restaurants.ui.main.data.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val locationProvider: LocationProvider,
    private val getRestaurants: GetRestaurants,
    private val updateRestaurant: UpdateRestaurant
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    fun getViewState() = _viewState

    private val _restaurants = MutableLiveData<List<RestaurantViewData>>()
    fun getRestaurants() = _restaurants

    init {
        fetchLocation()
    }

    private fun fetchLocation() = viewModelScope.launch {
        locationProvider.fetch { location ->
            loadRestaurants(location.first, location.second)
        }
    }

    private fun loadRestaurants(lat: Double, lon: Double) = viewModelScope.launch {
        _viewState.value = ViewState(loadingVisibility = true)

        when (val response = getRestaurants.execute(GetRestaurants.Request(lat, lon))) {
            is Result.GenericError -> _viewState.value = ViewState(
                errorVisibility = true,
                error = ViewState.Error(ViewState.Error.Type.GENERIC, response.error?.title)
            )
            is Result.NetworkError -> _viewState.value = ViewState(
                errorVisibility = true,
                error = ViewState.Error(ViewState.Error.Type.NETWORK)
            )
            is Result.Success -> {
                _viewState.value = ViewState(errorVisibility = false)
                _restaurants.postValue(response.value.toRestaurantViewDataList())
            }
        }

        _viewState.value = ViewState(loadingVisibility = false)
    }

    fun onFavCheckedChanged(id: String, checked: Boolean) = viewModelScope.launch {
        updateRestaurant.execute(UpdateRestaurant.Request(id, checked))
    }
}
