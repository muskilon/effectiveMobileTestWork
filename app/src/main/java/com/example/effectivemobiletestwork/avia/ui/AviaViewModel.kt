package com.example.effectivemobiletestwork.avia.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletestwork.avia.domain.RecommendationsInteractor
import kotlinx.coroutines.launch

class AviaViewModel(
    private val recommendationsInteractor: RecommendationsInteractor
) : ViewModel() {

    fun getRecommendations(){
        viewModelScope.launch {
            recommendationsInteractor.getRecommendations().collect{

            }
        }
    }
}