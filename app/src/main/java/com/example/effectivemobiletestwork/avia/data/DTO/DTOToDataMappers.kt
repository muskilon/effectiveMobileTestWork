package com.example.effectivemobiletestwork.avia.data.DTO

import com.example.effectivemobiletestwork.avia.domain.Offer

class DTOToDataMappers {
    fun recommendationsDTOToMainRecommendations(offers: List<OfferDTO>): List<Offer>{
        return offers.map {
            val newOffers = Offer(
                id = it.id,
                title = it.title,
                town = it.town,
                price = it.price.value.toInt()
            )
            newOffers
        }
    }

    fun ticketsDTOToTickets(tickets: List<Ticket>): List<Ticket> {
        return tickets
    }

    fun ticketsOffersDTOToTicketsOffers(ticketsOffers: List<TicketsOffers>) : List<TicketsOffers> {
        return ticketsOffers
    }
}