package com.example.effectivemobiletestwork.avia.ui.tickets

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.avia.model.Ticket
import com.example.effectivemobiletestwork.databinding.ItemTicketBinding

class TicketsAdapter : RecyclerView.Adapter<TicketsViewHolder>() {
    private val tickets = ArrayList<Ticket>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsViewHolder {
        return TicketsViewHolder(
            ItemTicketBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = tickets.size

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        holder.bind(tickets[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newTickets: List<Ticket>) {
        tickets.clear()
        tickets.addAll(newTickets)
        notifyDataSetChanged()
    }
}