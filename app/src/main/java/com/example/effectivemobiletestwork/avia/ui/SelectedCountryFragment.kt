package com.example.effectivemobiletestwork.avia.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.effectivemobiletestwork.R
import com.example.effectivemobiletestwork.app.App
import com.example.effectivemobiletestwork.avia.domain.model.TicketsOffer
import com.example.effectivemobiletestwork.databinding.FragmentSelectedCountryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

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

        setFragmentResultListener("directions") { _, bundle -> processBundle(bundle) }

        binding.arrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.getTicketsOffers()
        viewModel.observeOffers().observe(viewLifecycleOwner) { offers ->
            processOffers(offers)
        }

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
    @SuppressLint("DiscouragedApi")
    private fun getDrawableResourceByName(name: String): Int {
        val packageName = App.getAppResources().getResourcePackageName(R.drawable.em_1)
        val resId = App.getAppResources().getIdentifier(name, "drawable", packageName)
        return resId
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}