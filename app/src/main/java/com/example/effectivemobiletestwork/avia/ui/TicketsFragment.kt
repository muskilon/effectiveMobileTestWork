package com.example.effectivemobiletestwork.avia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.effectivemobiletestwork.databinding.FragmentTicketsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TicketsFragment : Fragment() {

    private var _binding: FragmentTicketsBinding? = null
    private val binding get() = _binding!!
    private val ticketsAdapter = TicketsAdapter()

    private val viewModel by viewModel<TicketsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("directionsTickets") { _, bundle ->
            processBundle(bundle)
        }
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
            val direction = bundle.getString("from") + "-" + bundle.getString("to")
            binding.direction.text = direction
            val details = bundle.getString("departureDate") + ", 1 пассажир"
            binding.details.text = details
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}