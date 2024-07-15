package com.example.effectivemobiletestwork.avia.ui.tickets

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.domain.Key
import com.example.effectivemobiletestwork.R
import com.example.effectivemobiletestwork.base.BaseFragment
import com.example.effectivemobiletestwork.databinding.FragmentTicketsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TicketsFragment : BaseFragment<FragmentTicketsBinding>(FragmentTicketsBinding::inflate) {
    private val ticketsAdapter = TicketsAdapter()
    private var direction: String? = null
    private var details: String? = null

    private val viewModel by viewModel<TicketsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(Key.DIRECTIONS_TICKETS) { _, bundle ->
            processBundle(bundle)
        }
        binding.direction.text = direction
        binding.details.text = details

        binding.arrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.ticketsRecycler.adapter = ticketsAdapter

        viewModel.getTickets()
        viewModel.observeTickets().observe(viewLifecycleOwner) { tickets ->
            ticketsAdapter.setData(tickets)
        }

    }
    private fun processBundle(bundle: Bundle) {
        if(!bundle.isEmpty) {
            direction = bundle.getString(Key.FROM) + "-" + bundle.getString(Key.TO)
            binding.direction.text = direction
            details = bundle.getString(Key.DEPARTURE_DATE) + getString(R.string.passengers)
            binding.details.text = details
        }
    }
}