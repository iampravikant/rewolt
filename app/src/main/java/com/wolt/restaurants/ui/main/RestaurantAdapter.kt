package com.wolt.restaurants.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wolt.restaurants.databinding.ItemRestaurantBinding
import com.wolt.restaurants.ui.ext.loadImageFromUrl
import com.wolt.restaurants.ui.main.data.RestaurantViewData

class RestaurantAdapter(private val listener: Listener) :
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    interface Listener {
        fun onFavCheckedChanged(id: String, checked: Boolean)
    }

    private val restaurants = arrayListOf<RestaurantViewData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder.binding) {
        val venue = restaurants[position]

        name.text = venue.name
        description.text = venue.description ?: ""
        fav.isChecked = venue.fav
        image.loadImageFromUrl(venue.imageUrl)

        fav.setOnClickListener { listener.onFavCheckedChanged(venue.id, fav.isChecked) }
    }

    override fun getItemCount() = restaurants.size

    @SuppressLint("NotifyDataSetChanged") // All the items will be replaced always
    fun setRestaurants(restaurants: List<RestaurantViewData>) {
        this.restaurants.clear()
        this.restaurants.addAll(restaurants)

        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root)
}
