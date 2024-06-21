package com.example.effectivemobiletestwork.avia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.avia.DepartureCityInteractor
import com.example.domain.avia.RecommendationsInteractor
import com.example.domain.avia.model.Offer
import kotlinx.coroutines.launch

class AviaViewModel(
    private val recommendationsInteractor: RecommendationsInteractor,
    private val departureCityInteractor: DepartureCityInteractor
) : ViewModel() {

    private val offersLiveData = MutableLiveData<List<Offer>>()

    fun observeOffers(): LiveData<List<Offer>> = offersLiveData

    fun getRecommendations() {
        viewModelScope.launch {
            recommendationsInteractor.getRecommendations().collect { offers ->
                when (offers) {
                    is com.example.domain.Resource.Data -> offersLiveData.postValue(offers.value)
                    is com.example.domain.Resource.ConnectionError -> Unit
                    is com.example.domain.Resource.NotFound -> Unit
                }
            }
        }
    }
    fun getDepartureCity(): String {
        return departureCityInteractor.getDepartureCity()
    }

    fun setDepartureCity(city: String) {
        return departureCityInteractor.setDepartureCity(city)
    }
}