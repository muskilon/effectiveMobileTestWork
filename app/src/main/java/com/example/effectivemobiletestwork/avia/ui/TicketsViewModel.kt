package com.example.effectivemobiletestwork.avia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletestwork.avia.data.dto.TicketDTO
import com.example.effectivemobiletestwork.avia.domain.TicketsInteractor
import com.example.effectivemobiletestwork.avia.domain.model.Ticket
import com.example.effectivemobiletestwork.domain.Resource
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
                    is Resource.Data -> offersLiveData.postValue(offers.value)
                    is Resource.ConnectionError -> Unit
                    is Resource.NotFound -> Unit
                }
            }
        }
    }
}