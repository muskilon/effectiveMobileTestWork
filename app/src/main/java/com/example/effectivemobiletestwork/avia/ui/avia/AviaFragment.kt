package com.example.effectivemobiletestwork.avia.ui.avia

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Key
import com.example.effectivemobiletestwork.R
import com.example.effectivemobiletestwork.base.BaseFragment
import com.example.effectivemobiletestwork.databinding.FragmentAviaBinding
import com.example.effectivemobiletestwork.root.RootActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AviaFragment : BaseFragment<FragmentAviaBinding>(FragmentAviaBinding::inflate) {
    private val aviaAdapter = AviaAdapter()
    private var bottomSheetBehavior = BottomSheetBehavior<View>()
    private var transitionJob: Job? = null
    private val viewModel by viewModel<AviaViewModel>()

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
                binding.include.to.requestFocus()
            }
        }

        val divider = MaterialDividerItemDecoration(
            binding.offersRecycler.context,
            RecyclerView.HORIZONTAL
        )
        with(binding) {
            offersRecycler.adapter = aviaAdapter
            offersRecycler.addItemDecoration(
                divider.apply {
                    setDividerThicknessResource(requireContext(), R.dimen.divider)
                    setDividerColorResource(requireContext(), R.color.black)
                    isLastItemDecorated = false
                }
            )
            from.addTextChangedListener(getTextWatcherForFrom())
            binding.from.setText(viewModel.getDepartureCity())

            include.to.addTextChangedListener(getTextWatcherForBottomSheetForTo())
        }
        setClickListeners()

        viewModel.getRecommendations()
        viewModel.observeOffers().observe(viewLifecycleOwner){ offers ->
            aviaAdapter.setData(offers)
        }

    }

    private fun getTextWatcherForFrom() = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.setDepartureCity(s.toString())
                    binding.include.from.text = s.toString()
        }

        override fun afterTextChanged(s: Editable?) = Unit
    }

    private fun setClickListeners() {
        with(binding) {
            to.setOnClickListener {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                include.to.requestFocus()
            }
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
            include.hardRoute.setOnClickListener {
                findNavController().navigate(R.id.action_aviaFragment_to_hardRouteFragment)
            }
            include.hotTickets.setOnClickListener {
                findNavController().navigate(R.id.action_aviaFragment_to_hotTicketsFragment)
            }
            include.weekend.setOnClickListener {
                findNavController().navigate(R.id.action_aviaFragment_to_weekendFragment)
            }
        }
    }

    private fun getTextWatcherForBottomSheetForTo() = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                transitionDebounce(s.toString(), binding.from.text.toString())
        }

        override fun afterTextChanged(s: Editable?) = Unit
    }
    fun transitionDebounce(to: String, from: String) {
        if (to.isEmpty()) {
            transitionJob?.cancel()
        } else {
            transitionJob?.cancel()
            transitionJob = lifecycleScope.launch {
                delay(TRANSITION_DEBOUNCE_DELAY)
                setFragmentResult(
                    Key.DIRECTIONS,
                    bundleOf(Key.FROM to from, Key.TO to to)
                )
                findNavController().navigate(R.id.action_aviaFragment_to_selectedCountryFragment)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        (activity as RootActivity).hideNavigation(true)
    }

    override fun onResume() {
        super.onResume()
        binding.include.to.setText(String())
        binding.to.setText(String())
    }

    companion object {
        private const val TRANSITION_DEBOUNCE_DELAY = 1000L
    }
}