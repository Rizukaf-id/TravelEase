package com.example.travelease

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.travelease.databinding.ActivityDetailPemesananTiketBinding
import com.example.travelease.databinding.ActivityPenumpangTersimpanBinding


class DetailPemesananTiket : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPemesananTiketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPemesananTiketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val armadaName = intent.getStringExtra("armada_name")
        val price = intent.getStringExtra("price")
        val departureTime = intent.getStringExtra("departure_time")
        val arrivalTime = intent.getStringExtra("arrival_time")
        val departureLocation = intent.getStringExtra("departure_location")
        val arrivalLocation = intent.getStringExtra("arrival_location")

        binding.kereta.text = armadaName
        binding.harga.text = price
        binding.departureTime.text = departureTime
        binding.arrivalTime.text = arrivalTime
        binding.departureStation.text = departureLocation
        binding.arrivalStation.text = arrivalLocation


        binding.linearLayout.setOnClickListener {
            val intent = Intent(this, DetailPenumpang::class.java)
            startActivity(intent)
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.btnBayar.setOnClickListener {
            val intent = Intent(this, MetodePembayaran::class.java)
            startActivity(intent)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val intent = Intent(this, PemesananKeretaLokal::class.java)
        startActivity(intent)
        super.onBackPressed()
    }


}