package com.example.effectivemobiletestwork.avia.data.DTO

import com.example.effectivemobiletestwork.avia.domain.model.Offer
import com.example.effectivemobiletestwork.avia.domain.model.TicketsOffer

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
                price = it.price.value,
                timeRange = it.timeRange.toString()
            )
            newOffers
        }
    }
}