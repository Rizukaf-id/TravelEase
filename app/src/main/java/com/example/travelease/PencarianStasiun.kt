package com.example.travelease

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.travelease.databinding.ActivityPencarianStasiunBinding
import com.example.travelease.databinding.ItemPencarianStasiunBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore

class PencarianStasiun : AppCompatActivity() {
    private lateinit var binding: ActivityPencarianStasiunBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPencarianStasiunBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.getBooleanExtra("departure", true)) {
            showLocationDeparture()
        } else if (intent.getBooleanExtra("arrival", true)) {
            showLocationArrival()
        }

        binding.imageUnion.setOnClickListener {
            val intent = Intent(this, PemesananKeretaLokal::class.java)
            startActivity(intent)
        }

        binding.linearLayout.setOnClickListener {
            val intent = Intent(this, PemesananKeretaLokal::class.java)
            startActivity(intent)
        }
    }

    fun showLocationDeparture(){
        val db = FirebaseFirestore.getInstance()
        db.collection("routes")
            .get()
            .addOnSuccessListener { result ->
                val locations = result.map { it.getString("departure_location") ?: "" }
                addLocationsToLinearLayout(locations)
            }
    }

    fun showLocationArrival(){
        val db = FirebaseFirestore.getInstance()
        db.collection("routes")
            .get()
            .addOnSuccessListener { result ->
                val locations = result.map { it.getString("arrival_location") ?: "" }
                addLocationsToLinearLayout(locations)
            }
    }


    fun addLocationsToLinearLayout(locations: List<String>) {
        val linearLayout = binding.linearLayout
        val inflater = LayoutInflater.from(this)

        for (location in locations) {
            val itemBinding = ItemPencarianStasiunBinding.inflate(inflater, linearLayout, false)
            itemBinding.stasiun.text = location
            itemBinding.root.setOnClickListener {
                val intent = Intent(this, PemesananKeretaLokal::class.java)
                intent.putExtra("selected_location", location)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
            linearLayout.addView(itemBinding.root)
        }
    }
}