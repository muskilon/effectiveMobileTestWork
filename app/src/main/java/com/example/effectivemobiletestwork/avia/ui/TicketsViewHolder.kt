package com.example.effectivemobiletestwork.avia.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobiletestwork.avia.data.dto.TicketDTO
import com.example.effectivemobiletestwork.databinding.ItemTicketBinding
import java.text.NumberFormat

class TicketsViewHolder(
    private val binding: ItemTicketBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(model: TicketDTO) {
        with(binding) {
            price.text = model.price.value
            departureTime.text = model.departure.date
            departureAirport.text = model.departure.airport
            arrivalTime.text = model.arrival.date
            arrivalAirport.text = model.arrival.airport
        }

    }

    private fun getPriceString(price: Int): String {
        val result = StringBuilder()
        with(result) {
            append("от ")
            append(NumberFormat.getInstance().format(price))
            append(" ₽")
        }
        return result.toString()
    }
}