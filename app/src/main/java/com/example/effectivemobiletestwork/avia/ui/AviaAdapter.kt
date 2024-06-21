package com.example.effectivemobiletestwork.avia.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.avia.model.Offer
import com.example.effectivemobiletestwork.databinding.ItemRecommendationsBinding

class AviaAdapter : RecyclerView.Adapter<AviaViewHolder>() {
    private val offers = ArrayList<Offer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AviaViewHolder {
        return AviaViewHolder(
            ItemRecommendationsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = offers.size

    override fun onBindViewHolder(holder: AviaViewHolder, position: Int) {
        holder.bind(offers[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newOffers: List<Offer>) {
        offers.clear()
        offers.addAll(newOffers)
        notifyDataSetChanged()
    }
}