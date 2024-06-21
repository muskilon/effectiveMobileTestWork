package com.example.data.avia.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.data.avia.dto.MainRecommendationDTO
import com.example.data.avia.dto.TicketsDTO
import com.example.data.avia.dto.TicketsOffersDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class RetrofitNetworkClient(
    private val context: Context,
    private val mockAPI: MockAPI
) : NetworkClient {
    override suspend fun getRecommendations(): com.example.domain.Resource<MainRecommendationDTO> {
        var recommedations: com.example.domain.Resource<MainRecommendationDTO>
        if (!isConnected()) return com.example.domain.Resource.ConnectionError(OFFLINE)
        withContext(Dispatchers.IO) {
            recommedations = try {
                mockAPI.getRecommendations().body()?.let {
                    com.example.domain.Resource.Data(it)
                } ?: com.example.domain.Resource.NotFound(NOT_FOUND)
            } catch (ex: IOException) {
                Log.e(REQUEST_ERROR_TAG, ex.toString())
                com.example.domain.Resource.ConnectionError(REQUEST_ERROR_TAG)
            }
        }
        return recommedations
    }

    override suspend fun getTicketsOffers(): com.example.domain.Resource<TicketsOffersDTO> {
        var ticketsOffers: com.example.domain.Resource<TicketsOffersDTO>
        if (!isConnected()) return com.example.domain.Resource.ConnectionError(OFFLINE)
        withContext(Dispatchers.IO) {
            ticketsOffers = try {
                mockAPI.getTicketsOffers().body()?.let {
                    com.example.domain.Resource.Data(it)
                } ?: com.example.domain.Resource.NotFound(NOT_FOUND)
            } catch (ex: IOException) {
                Log.e(REQUEST_ERROR_TAG, ex.toString())
                com.example.domain.Resource.ConnectionError(REQUEST_ERROR_TAG)
            }
        }
        return ticketsOffers
    }

    override suspend fun getTickets(): com.example.domain.Resource<TicketsDTO> {
        var tickets: com.example.domain.Resource<TicketsDTO>
        if (!isConnected()) return com.example.domain.Resource.ConnectionError(OFFLINE)
        withContext(Dispatchers.IO) {
            tickets = try {
                mockAPI.getTickets().body()?.let {
                    com.example.domain.Resource.Data(it)
                } ?: com.example.domain.Resource.NotFound(NOT_FOUND)
            } catch (ex: IOException) {
                Log.e(REQUEST_ERROR_TAG, ex.toString())
                com.example.domain.Resource.ConnectionError(REQUEST_ERROR_TAG)
            }
        }
        return tickets
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                ) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }

    companion object {
        private const val REQUEST_ERROR_TAG = "Произошла ошибка"
        private const val NOT_FOUND = "not found"
        private const val OFFLINE = "Проверьте подключение к интернету"
    }

}