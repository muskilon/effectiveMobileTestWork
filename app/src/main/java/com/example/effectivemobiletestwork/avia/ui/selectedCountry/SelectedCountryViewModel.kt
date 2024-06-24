package com.example.effectivemobiletestwork.avia.ui.selectedCountry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.avia.TicketsOffersInteractor
import com.example.domain.avia.model.TicketsOffer
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
                    is com.example.domain.Resource.Data -> offersLiveData.postValue(offers.value)
                    is com.example.domain.Resource.ConnectionError -> Unit
                    is com.example.domain.Resource.NotFound -> Unit
                }
            }
        }
    }
}