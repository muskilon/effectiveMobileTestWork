package com.example.effectivemobiletestwork.avia.ui

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobiletestwork.R
import com.example.effectivemobiletestwork.app.App
import com.example.effectivemobiletestwork.avia.domain.Offer
import com.example.effectivemobiletestwork.databinding.ItemRecommendationsBinding
import java.text.NumberFormat

class AviaViewHolder(
    private val binding: ItemRecommendationsBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Offer) {
        binding.city.text = model.town
        binding.title.text = model.title
        binding.price.text = getPriceString(model.price)
        binding.cover.setImageResource(getDrawableResourceByName(model.cover))
    }

    @SuppressLint("DiscouragedApi")
    private fun getDrawableResourceByName(name: String): Int {
        val packageName = App.getAppResources().getResourcePackageName(R.drawable.em_1)
        val resId = App.getAppResources().getIdentifier(name, "drawable", packageName)
        return resId
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