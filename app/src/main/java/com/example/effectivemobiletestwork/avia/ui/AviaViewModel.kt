package com.example.effectivemobiletestwork.avia.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletestwork.avia.domain.model.Offer
import com.example.effectivemobiletestwork.avia.domain.RecommendationsInteractor
import com.example.effectivemobiletestwork.avia.domain.TicketsInteractor
import com.example.effectivemobiletestwork.domain.Resource
import kotlinx.coroutines.launch

class AviaViewModel(
    private val recommendationsInteractor: RecommendationsInteractor,
    private val ticketsInteractor: TicketsInteractor
) : ViewModel() {

    private val offersLiveData = MutableLiveData<List<Offer>>()

    fun observeOffers(): LiveData<List<Offer>> = offersLiveData

    fun getRecommendations() {
        viewModelScope.launch {
            recommendationsInteractor.getRecommendations().collect { offers ->
                when (offers) {
                    is Resource.Data -> offersLiveData.postValue(offers.value)
                    is Resource.ConnectionError -> Unit
                    is Resource.NotFound -> Unit
                }
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
}