package com.example.effectivemobiletestwork.hotels.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.effectivemobiletestwork.R

class HotelsFragment : Fragment() {    override fun onCreateView(

    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View {
    return inflater.inflate(R.layout.fragment_hotels, container, false)
}
}