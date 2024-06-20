package com.example.effectivemobiletestwork.avia.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletestwork.avia.domain.TicketsOffersInteractor
import com.example.effectivemobiletestwork.avia.domain.model.TicketsOffer
import com.example.effectivemobiletestwork.domain.Resource
import kotlinx.coroutines.launch

class SelectedCountryViewModel(
    private val ticketsOffersInteractor: TicketsOffersInteractor
) : ViewModel() {
    private val offersLiveData = MutableLiveData<List<TicketsOffer>>()

    fun observeOffers(): LiveData<List<TicketsOffer>> = offersLiveData

    fun getTicketsOffers() {
        viewModelScope.launch {
            ticketsOffersInteractor.getTicketsOffers().collect { offers ->
                when (offers) {
                    is Resource.Data -> offersLiveData.postValue(offers.value)
                    is Resource.ConnectionError -> Unit
                    is Resource.NotFound -> Unit
                }
                Log.d("TAG_TICKETS_OFFERS", offers.toString())
            }
        }
    }
}