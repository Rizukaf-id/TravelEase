package com.example.travelease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.travelease.databinding.FragmentHomeBinding
import com.example.travelease.databinding.FragmentTicketBinding

class Ticket : Fragment() {

    private var _binding : FragmentTicketBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using ViewBinding
        _binding = FragmentTicketBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Now you can safely access views from the binding
        binding.historyButton.setOnClickListener {
            Toast.makeText(requireContext(), "Image clicked", Toast.LENGTH_SHORT).show()
        }
    }
}