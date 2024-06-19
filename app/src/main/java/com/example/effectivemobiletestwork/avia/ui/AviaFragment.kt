package com.example.effectivemobiletestwork.avia.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.effectivemobiletestwork.databinding.FragmentAviaBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AviaFragment : Fragment() {
    private var _binding: FragmentAviaBinding? = null
    private val binding get() = _binding!!
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

        viewModel.getRecommendations()
        Log.d("TAG", "OINK")
    }
}