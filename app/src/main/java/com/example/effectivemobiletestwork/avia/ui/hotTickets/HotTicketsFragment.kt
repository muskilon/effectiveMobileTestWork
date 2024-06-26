package com.example.effectivemobiletestwork.avia.ui.hotTickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.effectivemobiletestwork.R
import com.example.effectivemobiletestwork.databinding.FragmentAviaBinding
import com.example.effectivemobiletestwork.databinding.FragmentHardRouteBinding
import com.example.effectivemobiletestwork.databinding.FragmentHotTicketsBinding
import com.example.effectivemobiletestwork.databinding.FragmentTicketsBinding

class HotTicketsFragment : Fragment() {
    private var _binding: FragmentHotTicketsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: Toolbar = binding.toolbar
        toolbar.setNavigationOnClickListener{
            findNavController().navigateUp()
        }
    }
}