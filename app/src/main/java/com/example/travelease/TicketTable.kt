package com.example.travelease

data class TicketTable(
    val ticketId: String,
    val scheduleId: String,
    val armadaName: String,
    val type: String,
    val ticketClass: String,
    val seatCapacity: Int,
    val price: Float
)
