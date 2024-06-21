package com.example.effectivemobiletestwork.avia.data.dto

import com.example.domain.avia.model.Offer
import com.example.domain.avia.model.Ticket
import com.example.domain.avia.model.TicketsOffer
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.DurationUnit

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

    fun ticketsDTOToTickets(tickets: List<TicketDTO>): List<Ticket> {
        return tickets.map {
            val newTickets = Ticket(
                id = it.id,
                price = getPriceString(it.price.value.toInt()),
                arrivalAirport = it.arrival.airport,
                arrivalDate = getDate(it.arrival.date),
                departureDate = getDate(it.departure.date),
                departureAirport = it.departure.airport,
                timeInFlight = getDuration(it.departure.date, it.arrival.date),
                options = getOptions(it.hasTransfer, getDuration(it.departure.date, it.arrival.date)),
                badge = it.badge,
                transfer = it.hasTransfer
            )
            newTickets
        }
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
    private fun getOptions(transfer: Boolean, duration: String): String {
        val options = StringBuilder()
        options.append("${duration}ч в пути")
        if (!transfer) options.append(" / Без пересадок")
        return options.toString()
    }

    private fun getDuration(departure: String, arrival: String): String {
        val diff = convertToDate(arrival).time - convertToDate(departure).time
        val hours = diff.milliseconds.toDouble(DurationUnit.HOURS)
        return String.format(Locale.US,"%.1f", hours)
    }

    private fun getDate(date: String): String {
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(convertToDate(date))
    }

    private fun convertToDate(date: String): Date {
        val dateFormat = try {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return if (dateFormat is Date) dateFormat
        else Calendar.getInstance().time
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