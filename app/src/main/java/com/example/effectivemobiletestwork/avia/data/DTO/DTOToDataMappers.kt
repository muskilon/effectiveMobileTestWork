package com.example.effectivemobiletestwork.avia.data.DTO

import com.example.effectivemobiletestwork.avia.domain.model.Offer
import com.example.effectivemobiletestwork.avia.domain.model.TicketsOffer
import java.text.NumberFormat

class DTOToDataMappers {
    fun recommendationsDTOToMainRecommendations(offers: List<OfferDTO>): List<Offer>{
        return offers.map {
            val newOffers = Offer(
                id = it.id,
                title = it.title,
                town = it.town,
                price = it.price.value.toInt(),
                cover = "em_" + it.id
            )
            newOffers
        }
    }

    fun ticketsDTOToTickets(tickets: List<Ticket>): List<Ticket> {
        return tickets
    }

    fun ticketsOffersDTOToTicketsOffers(ticketOfferDTOS: List<TicketOffersDTO>) : List<TicketsOffer> {
        return ticketOfferDTOS.map {
            val newOffers = TicketsOffer(
                id = it.id,
                title = it.title,
                price = getPriceString(it.price.value.toInt()),
                timeRange = getTimeRangeString(it.timeRange)
            )
            newOffers
        }
    }

    private fun getTimeRangeString(array: ArrayList<String>): String {
        val timeRange = StringBuilder()
        array.forEachIndexed { index, time ->
            timeRange.append(time)
            if(index != array.size-1) timeRange.append("  ")
        }
        return timeRange.toString()
    }

    private fun getPriceString(price: Int): String {
        val result = StringBuilder()
        with(result) {
            append(NumberFormat.getInstance().format(price))
            append(" ₽")
        }
        return result.toString()
    }
}