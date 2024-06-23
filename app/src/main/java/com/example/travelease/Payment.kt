package com.example.travelease

data class Payment(
    val paymentId: String,
    val bookingId: String,
    val paymentDate: String,
    val amount: Float,
    val paymentMethod: String,
    val status: String
)
