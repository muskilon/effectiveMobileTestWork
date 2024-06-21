package com.example.data.avia

import android.content.SharedPreferences

class SharedStorage(private val sharedPreferences: SharedPreferences) {

    fun getDepartureCity(): String? {
        return sharedPreferences.getString(DEPARTURE_CITY, null)
    }
    fun setDepartureCity(city: String) {
        sharedPreferences.edit()
            .putString(DEPARTURE_CITY, city)
            .apply()
    }

    companion object {
        const val DEPARTURE_CITY = "departureCity"
    }
}