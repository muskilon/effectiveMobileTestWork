package com.example.effectivemobiletestwork.avia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.avia.TicketsInteractor
import com.example.domain.avia.model.Ticket
import kotlinx.coroutines.launch

class TicketsViewModel(
    private val ticketsInteractor: TicketsInteractor
) : ViewModel() {

    private val offersLiveData = MutableLiveData<List<Ticket>>()

    fun observeTickets(): LiveData<List<Ticket>> = offersLiveData

    fun getTickets() {
        viewModelScope.launch {
            ticketsInteractor.getTickets().collect { offers ->
                when (offers) {
                    is com.example.domain.Resource.Data -> offersLiveData.postValue(offers.value)
                    is com.example.domain.Resource.ConnectionError -> Unit
                    is com.example.domain.Resource.NotFound -> Unit
                }
            }
        }
    }
}