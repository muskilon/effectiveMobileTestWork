package com.example.effectivemobiletestwork.avia.ui

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobiletestwork.R
import com.example.domain.avia.model.Ticket
import com.example.effectivemobiletestwork.databinding.ItemTicketBinding

class TicketsViewHolder(
    private val binding: ItemTicketBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Ticket) {
        val marginTop = itemView.resources.getDimension(R.dimen.margin_top_ticket)
        with(binding) {
            price.text = model.price
            departureTime.text = model.departureDate
            departureAirport.text = model.departureAirport
            arrivalTime.text = model.arrivalDate
            arrivalAirport.text = model.arrivalAirport
            options.text = model.options
        }
        if (model.badge.isNullOrBlank()) {
            binding.bage.isVisible = false
            binding.cardTicket.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                setMargins(0, marginTop.toInt(), 0, 0)
            }
        }
        else {
            binding.bage.isVisible = true
            binding.bage.text = model.badge

        }
    }
}