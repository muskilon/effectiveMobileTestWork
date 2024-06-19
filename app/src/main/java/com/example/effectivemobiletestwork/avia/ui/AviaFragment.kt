package com.example.effectivemobiletestwork.avia.ui

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobiletestwork.R
import com.example.effectivemobiletestwork.app.App
import com.example.effectivemobiletestwork.databinding.FragmentAviaBinding
import com.example.effectivemobiletestwork.root.RootActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.divider.MaterialDividerItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class AviaFragment : Fragment() {
    private var _binding: FragmentAviaBinding? = null
    private val binding get() = _binding!!
    private val aviaAdapter = AviaAdapter()
    private var bottomSheetBehavior = BottomSheetBehavior<View>()
    private val viewModel by viewModel<AviaViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAviaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        (activity as RootActivity).hideNavigation(true)
                    }
                    else -> (activity as RootActivity).hideNavigation(false)
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })

        binding.to.onFocusChangeListener = View.OnFocusChangeListener { _, isFocus ->
            if(isFocus) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        val divider = MaterialDividerItemDecoration(
            binding.offersRecycler.context,
            RecyclerView.HORIZONTAL
        )
        binding.offersRecycler.adapter = aviaAdapter
        binding.offersRecycler.addItemDecoration(
            divider.apply {
                setDividerThicknessResource(requireContext(),R.dimen.divider)
                setDividerColorResource(requireContext(),R.color.black)
                isLastItemDecorated = false
            }
        )

        viewModel.getRecommendations()
        viewModel.observeOffers().observe(viewLifecycleOwner){ offers ->
            aviaAdapter.setData(offers)
        }

        viewModel.getTickets()
        viewModel.getTicketsOffers()
    }
}