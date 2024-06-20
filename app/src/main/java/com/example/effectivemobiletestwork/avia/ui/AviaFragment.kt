package com.example.effectivemobiletestwork.avia.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobiletestwork.R
import com.example.effectivemobiletestwork.databinding.FragmentAviaBinding
import com.example.effectivemobiletestwork.root.RootActivity
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
        bottomSheetBehavior.skipCollapsed = true
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
            override fun onSlide(bottomSheet: View, slideOffset: Float) = Unit
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
        with(binding) {
            to.setOnClickListener { bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED }
            offersRecycler.adapter = aviaAdapter
            offersRecycler.addItemDecoration(
                divider.apply {
                    setDividerThicknessResource(requireContext(), R.dimen.divider)
                    setDividerColorResource(requireContext(), R.color.black)
                    isLastItemDecorated = false
                }
            )
            from.addTextChangedListener(getTextWatcher())
            include.from.addTextChangedListener(getTextWatcher())

            include.everywhere.setOnClickListener {
                include.to.setText(R.string.everywhere)
            }
            include.istanbulClick.setOnClickListener {
                include.to.setText(R.string.istanbul)
            }
            include.sochiClick.setOnClickListener {
                include.to.setText(R.string.sochi)
            }
            include.phuketClick.setOnClickListener {
                include.to.setText(R.string.phuket)
            }
            include.clear.setOnClickListener{
                include.to.setText(String())
            }
        }

        viewModel.getRecommendations()
        viewModel.observeOffers().observe(viewLifecycleOwner){ offers ->
            aviaAdapter.setData(offers)
        }

        viewModel.getTickets()
        viewModel.getTicketsOffers()
    }

    private fun getTextWatcher() = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) = Unit
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}