package com.example.travelease

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.travelease.databinding.ActivityStrukPembayaranBinding
import com.google.firebase.firestore.FirebaseFirestore

class StrukPembayaran : AppCompatActivity() {
    private lateinit var binding: ActivityStrukPembayaranBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStrukPembayaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val armadaName = intent.getStringExtra("armada_name")
        val price = intent.getStringExtra("price")
        val departureTime = intent.getStringExtra("departure_time")
        val arrivalTime = intent.getStringExtra("arrival_time")
        val departureLocation = intent.getStringExtra("departure_location")
        val arrivalLocation = intent.getStringExtra("arrival_location")

        Log.d("StrukPembayaran", "Data yang diterima: $armadaName, $price, $departureTime, $arrivalTime, $departureLocation, $arrivalLocation")

        binding.namaArmada.text = armadaName
        binding.totalPembayaran.text = price
        binding.departureLocation.text = departureLocation
        binding.arrivalLocation.text = arrivalLocation
        binding.departureTime.text = departureTime
        binding.arrivalTime.text = arrivalTime
        val paymentMethod = "Transfer BCA"

        val sale = hashMapOf(
            "ticket_name" to armadaName,
            "price" to price,
            "departure_time" to departureTime,
            "arrival_time" to arrivalTime,
            "departure_location" to departureLocation,
            "arrival_location" to arrivalLocation,
            "payment_method" to paymentMethod
        )

        binding.closeButton.setOnClickListener(){
            val db = FirebaseFirestore.getInstance()
            db.collection("sales")
                .add(sale)
                .addOnCompleteListener{ documentReference ->
                    Toast.makeText(this, "Pembayaran Berhasil", Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener(){
                    Toast.makeText(this, "Pembayaran Gagal", Toast.LENGTH_SHORT).show()
                }
            finish()
        }
    }
}