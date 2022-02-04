package com.wolt.restaurants.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.wolt.restaurants.R
import com.wolt.restaurants.databinding.ActivityMainBinding
import com.wolt.restaurants.ui.ext.setVisible
import com.wolt.restaurants.ui.main.data.ViewState.Error.Type.GENERIC
import com.wolt.restaurants.ui.main.data.ViewState.Error.Type.NETWORK
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RestaurantAdapter.Listener {

    private val viewModel: MainViewModel by viewModels()
    private val adapter = RestaurantAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.restaurantList.adapter = adapter

        observeViewModel(binding)
    }

    private fun observeViewModel(binding: ActivityMainBinding) {
        viewModel.getViewState().observe(this) { state ->
            state.loadingVisibility?.let { visible ->
                binding.loading.setVisible(visible)
            }

            state.errorVisibility?.let { visible ->
                binding.error.setVisible(visible)
            }

            state.error?.let { error ->
                when (error.type) {
                    GENERIC -> binding.error.setText(R.string.error_unknown)
                    NETWORK -> binding.error.text = error.text ?: getString(R.string.error_network)
                }
            }
        }

        viewModel.getRestaurants().observe(this) {
            adapter.setRestaurants(it)
        }
    }

    override fun onFavCheckedChanged(id: String, checked: Boolean) {
        viewModel.onFavCheckedChanged(id, checked)
    }
}
