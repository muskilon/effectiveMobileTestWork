package com.example.effectivemobiletestwork.avia.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletestwork.avia.domain.RecommendationsInteractor
import com.example.effectivemobiletestwork.avia.domain.TicketsInteractor
import kotlinx.coroutines.launch

class AviaViewModel(
    private val recommendationsInteractor: RecommendationsInteractor,
    private val ticketsInteractor: TicketsInteractor
) : ViewModel() {

    fun getRecommendations() {
        viewModelScope.launch {
            recommendationsInteractor.getRecommendations().collect { offers ->
                Log.d("TAG", offers.toString())
            }
        }
    }

    fun getTickets() {
        viewModelScope.launch {
            ticketsInteractor.getTickets().collect {tickets ->
                Log.d("TAG", tickets.toString())
            }
        }
    }

    fun getTicketsOffers() {
        viewModelScope.launch {
            ticketsInteractor.getTicketsOffers().collect {ticketsOffers ->
                Log.d("TAG", ticketsOffers.toString())
            }
        }
    }
}