package com.example.effectivemobiletestwork.avia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.effectivemobiletestwork.R
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
    private var departureDate = Calendar.getInstance().time

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

        binding.date.text = getDateForFilter(departureDate)

        binding.changeDirection.setOnClickListener {
            val temp = binding.from.text
            binding.from.text = binding.to.text
            binding.to.text = temp
        }
        binding.clear.setOnClickListener {
            binding.to.text = String()
        }

        binding.date.setOnClickListener {
            binding.calendar.isVisible = true
            binding.calendar.tag = "departureDate"
        }
        binding.backDate.setOnClickListener {
            binding.calendar.isVisible = true
            binding.calendar.tag = "backDate"
        }
        binding.allTickets.setOnClickListener {
            setFragmentResult(
                "directionsTickets",
                bundleOf("from" to binding.from.text, "to" to binding.to.text, "departureDate" to getDateForTickets(departureDate))
            )
            findNavController().navigate(R.id.action_selectedCountryFragment_to_ticketsFragment)
        }

        binding.calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calender = Calendar.getInstance()
            calender.set(year, month, dayOfMonth)
            if (binding.calendar.tag == "departureDate") {
                departureDate = calender.time
                binding.date.text = getDateForFilter(calender.time)
            }
            else {
                binding.backDate.text = getDateForFilter(calender.time)
                binding.backDate.setCompoundDrawables(null, null, null, null)
            }
            binding.calendar.isVisible = false
        }

        viewModel.getTicketsOffers()
        viewModel.observeOffers().observe(viewLifecycleOwner) { offers ->
            processOffers(offers)
        }

    }

    private fun getDateForFilter(date: Date): String {
        val formatter = SimpleDateFormat("dd MMM, EEE", Locale.getDefault())
        val formatedDate = formatter.format(date).replace(".", "")
        return formatedDate
    }

    private fun getDateForTickets(date: Date): String {
        val formatter = SimpleDateFormat("dd MMMM", Locale.getDefault())
        val formatedDate = formatter.format(date)
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