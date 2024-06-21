package com.example.effectivemobiletestwork.avia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletestwork.avia.domain.DepartureCityInteractor
import com.example.effectivemobiletestwork.avia.domain.RecommendationsInteractor
import com.example.effectivemobiletestwork.avia.domain.model.Offer
import com.example.effectivemobiletestwork.domain.Resource
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
                    is Resource.Data -> offersLiveData.postValue(offers.value)
                    is Resource.ConnectionError -> Unit
                    is Resource.NotFound -> Unit
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