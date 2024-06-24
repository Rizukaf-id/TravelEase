package com.example.travelease

import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.travelease.databinding.ActivityInventarisViewBinding
import com.google.firebase.firestore.FirebaseFirestore

class InventarisView : AppCompatActivity() {
    private var _binding: ActivityInventarisViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInventarisViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val db = FirebaseFirestore.getInstance()
        val salesRef = db.collection("sales")

        var totalSales = 0
        var totalPrice = 0.0

        salesRef.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val price = document.getString("ticket_price")?.toDouble() ?: 0.0
                    totalPrice += price
                    totalSales++
                }

                binding.tiketTerjual.text = totalSales.toString()
                binding.totalSales.text = totalPrice.toString()
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

        binding.logout.setOnClickListener(){
            finish()
        }
    }
}