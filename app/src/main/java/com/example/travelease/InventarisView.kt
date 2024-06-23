package com.example.travelease

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.travelease.databinding.ActivityInventarisViewBinding

class InventarisView : AppCompatActivity() {
    private var _binding: ActivityInventarisViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInventarisViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Find the buttons and EditText
        val decreaseButton: AppCompatButton = findViewById(R.id.decreaseButton)
        val increaseButton: AppCompatButton = findViewById(R.id.increaseButton)
        val stockEditText: EditText = findViewById(R.id.stockEditText)
        val confirmButton: Button = findViewById(R.id.confirmButton)

        val originalStock = stockEditText.text.toString().toInt()

        decreaseButton.setOnClickListener {
            val currentStock = stockEditText.text.toString().toInt()
            if (currentStock > 0) {
                stockEditText.setText((currentStock - 1).toString())
            }
        }

        increaseButton.setOnClickListener {
            val currentStock = stockEditText.text.toString().toInt()
            stockEditText.setText((currentStock + 1).toString())
        }

        stockEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val currentStock = s.toString().toIntOrNull() ?: 0
                confirmButton.isEnabled = currentStock != originalStock
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}