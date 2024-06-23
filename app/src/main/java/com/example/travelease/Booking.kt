package com.example.travelease

data class Booking(
    val bookingId: String,
    val userId: String,
    val bookingDate: String,
    val totalPrice: Float,
    val status: String
)
