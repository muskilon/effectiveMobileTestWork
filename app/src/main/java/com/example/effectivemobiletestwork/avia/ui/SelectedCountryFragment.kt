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
import com.example.domain.Key
import com.example.effectivemobiletestwork.R
import com.example.domain.avia.model.TicketsOffer
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
    private var departure: String? = null
    private var arrival: String? = null

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

        setFragmentResultListener(Key.DIRECTIONS) { _, bundle -> processBundle(bundle) }

        with(binding){
            calendar.isVisible = false
            from.text = departure
            binding.to.text = arrival

            arrowBack.setOnClickListener {
                findNavController().navigateUp()
            }

            date.text = getDateForFilter(departureDate)

            changeDirection.setOnClickListener {
                val temp = from.text
                from.text = binding.to.text
                to.text = temp
            }
            clear.setOnClickListener {
                to.text = String()
            }

            date.setOnClickListener {
                calendar.isVisible = true
                calendar.tag = Key.DEPARTURE_DATE
            }
            backDate.setOnClickListener {
                calendar.isVisible = true
                calendar.tag = Key.BACK_DATE
            }
            allTickets.setOnClickListener {
                setFragmentResult(
                    Key.DIRECTIONS_TICKETS,
                    bundleOf(
                        Key.FROM to from.text,
                        Key.TO to to.text,
                        Key.DEPARTURE_DATE to getDateForTickets(departureDate)
                    )
                )
                findNavController().navigate(R.id.action_selectedCountryFragment_to_ticketsFragment)
            }

            calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
                val calender = Calendar.getInstance()
                calender.set(year, month, dayOfMonth)
                if (calendar.tag == Key.DEPARTURE_DATE) {
                    departureDate = calender.time
                    date.text = getDateForFilter(calender.time)
                } else {
                    backDate.text = getDateForFilter(calender.time)
                    backDate.setCompoundDrawables(null, null, null, null)
                }
                calendar.isVisible = false
            }
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
        with(binding){
            company1.text = offers[0].title
            price1.text = offers[0].price
            timeTable1.text = offers[0].timeRange

            company2.text = offers[1].title
            price2.text = offers[1].price
            timeTable2.text = offers[1].timeRange

            company3.text = offers[2].title
            price3.text = offers[2].price
            timeTable3.text = offers[2].timeRange
        }
    }

    private fun processBundle(bundle: Bundle) {
        if(!bundle.isEmpty) {
            binding.from.text = bundle.getString(Key.FROM)
            binding.to.text = bundle.getString(Key.TO)
            departure = bundle.getString(Key.FROM)
            arrival = bundle.getString(Key.TO)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}