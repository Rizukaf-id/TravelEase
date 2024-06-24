package com.example.travelease

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.travelease.databinding.ActivityPemesananKeretaLokalBinding

class PemesananKeretaLokal : AppCompatActivity() {
    private lateinit var binding: ActivityPemesananKeretaLokalBinding

    public var departure: Boolean = false
    public var arrival: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemesananKeretaLokalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.departure.setOnClickListener {
            val intent = Intent(this, PencarianStasiun::class.java)
            intent.putExtra("departure", true)
            intent.putExtra("arrival", false)
            startActivityForResult(intent, DEPARTURE_REQUEST_CODE)
        }

        binding.arrival.setOnClickListener {
            val intent = Intent(this, PencarianStasiun::class.java)
            intent.putExtra("departure", false)
            intent.putExtra("arrival", true)
            startActivityForResult(intent, ARRIVAL_REQUEST_CODE)
        }

        binding.cariKereta.setOnClickListener {
            val intent = Intent(this, PilihKereta::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                DEPARTURE_REQUEST_CODE -> {
                    val selectedLocation = data?.getStringExtra("selected_location")
                    binding.textDari1.text = selectedLocation
                }
                ARRIVAL_REQUEST_CODE -> {
                    val selectedLocation = data?.getStringExtra("selected_location")
                    binding.textKe1.text = selectedLocation
                }
            }
        }
    }

    companion object {
        const val DEPARTURE_REQUEST_CODE = 1
        const val ARRIVAL_REQUEST_CODE = 2
    }
}