package com.example.travelease

data class Sale(
    val salesId: String,
    val ticketId: String,
    val paymentId: String,
    val date: String,
    val totalSales: Float,
    val totalBook: Int,
    val totalTicketSold: Int
)
