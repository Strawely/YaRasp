package com.solom.yarasp.model

data class TicketsInfo (
    var etMarket: Boolean = false,
    var places: MutableList<Place> = arrayListOf()
)