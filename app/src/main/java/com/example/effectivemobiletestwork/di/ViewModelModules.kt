package com.example.effectivemobiletestwork.di

import com.example.effectivemobiletestwork.avia.ui.AviaViewModel
import com.example.effectivemobiletestwork.avia.ui.SelectedCountryViewModel
import com.example.effectivemobiletestwork.avia.ui.TicketsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel {
        AviaViewModel(recommendationsInteractor = get())
    }
    viewModel {
        SelectedCountryViewModel(ticketsOffersInteractor = get())
    }
    viewModel {
        TicketsViewModel(ticketsInteractor = get())
    }
}