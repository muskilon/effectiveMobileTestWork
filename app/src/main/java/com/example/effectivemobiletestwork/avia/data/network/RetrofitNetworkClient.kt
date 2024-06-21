package com.example.effectivemobiletestwork.avia.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.effectivemobiletestwork.avia.data.dto.MainRecommendationDTO
import com.example.effectivemobiletestwork.avia.data.dto.TicketsDTO
import com.example.effectivemobiletestwork.avia.data.dto.TicketsOffersDTO
import com.example.effectivemobiletestwork.domain.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class RetrofitNetworkClient(
    private val context: Context,
    private val mockAPI: MockAPI
) : NetworkClient {
    override suspend fun getRecommendations(): Resource<MainRecommendationDTO> {
        var recommedations: Resource<MainRecommendationDTO>
        if (!isConnected()) return Resource.ConnectionError(OFFLINE)
        withContext(Dispatchers.IO) {
            recommedations = try {
                mockAPI.getRecommendations().body()?.let {
                    Resource.Data(it)
                } ?: Resource.NotFound(NOT_FOUND)
            } catch (ex: IOException) {
                Log.e(REQUEST_ERROR_TAG, ex.toString())
                Resource.ConnectionError(REQUEST_ERROR_TAG)
            }
        }
        return recommedations
    }

    override suspend fun getTicketsOffers(): Resource<TicketsOffersDTO> {
        var ticketsOffers: Resource<TicketsOffersDTO>
        if (!isConnected()) return Resource.ConnectionError(OFFLINE)
        withContext(Dispatchers.IO) {
            ticketsOffers = try {
                mockAPI.getTicketsOffers().body()?.let {
                    Resource.Data(it)
                } ?: Resource.NotFound(NOT_FOUND)
            } catch (ex: IOException) {
                Log.e(REQUEST_ERROR_TAG, ex.toString())
                Resource.ConnectionError(REQUEST_ERROR_TAG)
            }
        }
        return ticketsOffers
    }

    override suspend fun getTickets(): Resource<TicketsDTO> {
        var tickets: Resource<TicketsDTO>
        if (!isConnected()) return Resource.ConnectionError(OFFLINE)
        withContext(Dispatchers.IO) {
            tickets = try {
                mockAPI.getTickets().body()?.let {
                    Resource.Data(it)
                } ?: Resource.NotFound(NOT_FOUND)
            } catch (ex: IOException) {
                Log.e(REQUEST_ERROR_TAG, ex.toString())
                Resource.ConnectionError(REQUEST_ERROR_TAG)
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