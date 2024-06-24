package com.example.effectivemobiletestwork.di

import com.example.effectivemobiletestwork.avia.ui.avia.AviaViewModel
import com.example.effectivemobiletestwork.avia.ui.selectedCountry.SelectedCountryViewModel
import com.example.effectivemobiletestwork.avia.ui.tickets.TicketsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel {
        AviaViewModel(recommendationsInteractor = get(), departureCityInteractor = get())
    }
    viewModel {
        SelectedCountryViewModel(ticketsOffersInteractor = get())
    }
    viewModel {
        TicketsViewModel(ticketsInteractor = get())
    }
}