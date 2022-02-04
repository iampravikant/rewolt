package com.wolt.restaurants.ui.main.data

data class ViewState(
    val loadingVisibility: Boolean? = null,
    val errorVisibility: Boolean? = null,
    val error: Error? = null
) {
    data class Error(
        val type: Type,
        val text: String? = null
    ) {
        enum class Type {
            GENERIC, NETWORK
        }
    }
}
