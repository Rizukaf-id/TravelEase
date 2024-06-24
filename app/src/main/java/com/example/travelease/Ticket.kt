package com.example.travelease

import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.travelease.databinding.FragmentHomeBinding
import com.example.travelease.databinding.FragmentTicketBinding
import com.example.travelease.databinding.TicketItemBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot

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
        val db = FirebaseFirestore.getInstance()

        // Get sales collection
        db.collection("sales")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    displayTicket(document)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        return binding.root
    }

    private fun displayTicket(document: QueryDocumentSnapshot?) {
        val ticket_name = document?.getString("ticket_name")
        val departureTime = document?.getString("departure_time")
        val arrivalTime = document?.getString("arrival_time")
        val departureLocation = document?.getString("departure_location")
        val arrivalLocation = document?.getString("arrival_location")
        val ticketPrice = document?.getString("price")
        val formatPrice = "Rp $ticketPrice"

        // Set the data to the views
        val itemBinding = TicketItemBinding.inflate(layoutInflater)
        itemBinding.ticketCode.text = ticket_name
        itemBinding.departureTime.text = departureTime
        itemBinding.arrivalTime.text = arrivalTime
        itemBinding.departureLocation.text = departureLocation
        itemBinding.arrivalLocation.text = arrivalLocation
        itemBinding.ticketPrice.text = formatPrice

        // Add the view to your layout
        binding.ticketContainer.addView(itemBinding.root)
    }
}