package com.solom.yarasp.model

data class Place (
    var currency: String = "",
    var name: String = "",
    var price: Price = Price()
)