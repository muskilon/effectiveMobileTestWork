package com.example.effectivemobiletestwork.avia.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobiletestwork.avia.domain.model.Ticket
import com.example.effectivemobiletestwork.databinding.ItemTicketBinding

class TicketsViewHolder(
    private val binding: ItemTicketBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Ticket) {
        with(binding) {
            price.text = model.price
            departureTime.text = model.departureDate
            departureAirport.text = model.departureAirport
            arrivalTime.text = model.arrivalDate
            arrivalAirport.text = model.arrivalAirport
            options.text = model.options
        }
    }
}