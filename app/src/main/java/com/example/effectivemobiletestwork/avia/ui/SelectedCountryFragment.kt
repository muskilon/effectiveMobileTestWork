package com.example.effectivemobiletestwork.avia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.effectivemobiletestwork.avia.domain.model.TicketsOffer
import com.example.effectivemobiletestwork.databinding.FragmentSelectedCountryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class SelectedCountryFragment : Fragment() {
    private var _binding: FragmentSelectedCountryBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<SelectedCountryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectedCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calendar.isVisible = false

        setFragmentResultListener("directions") { _, bundle -> processBundle(bundle) }

        binding.arrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.date.text = getDate(Calendar.getInstance().time)

        binding.changeDirection.setOnClickListener {
            val temp = binding.from.text
            binding.from.text = binding.to.text
            binding.to.text = temp
        }

        binding.date.setOnClickListener {
            binding.calendar.isVisible = true
            binding.calendar.tag = "departureDate"
        }
        binding.backDate.setOnClickListener {
            binding.calendar.isVisible = true
            binding.calendar.tag = "backDate"
        }

        binding.calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calender = Calendar.getInstance()
            calender.set(year, month, dayOfMonth)
            if (binding.calendar.tag == "departureDate") binding.date.text = getDate(calender.time)
            else {
                binding.backDate.text = getDate(calender.time)
                binding.backDate.setCompoundDrawables(null, null, null, null)
            }
            binding.calendar.isVisible = false
        }

        viewModel.getTicketsOffers()
        viewModel.observeOffers().observe(viewLifecycleOwner) { offers ->
            processOffers(offers)
        }

    }

    private fun getDate(date: Date): String {
        val formatter = SimpleDateFormat("dd MMM, EEE", Locale.getDefault())
        val formatedDate = formatter.format(date).replace(".", "")
        return formatedDate
    }

    private fun processOffers(offers: List<TicketsOffer>) {
        binding.company1.text = offers[0].title
        binding.price1.text = offers[0].price
        binding.timeTable1.text = offers[0].timeRange

        binding.company2.text = offers[1].title
        binding.price2.text = offers[1].price
        binding.timeTable2.text = offers[1].timeRange

        binding.company3.text = offers[2].title
        binding.price3.text = offers[2].price
        binding.timeTable3.text = offers[2].timeRange
    }

    private fun processBundle(bundle: Bundle) {
        if(!bundle.isEmpty) {
            binding.from.text = bundle.getString("from")
            binding.to.text = bundle.getString("to")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}