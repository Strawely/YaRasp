package com.solom.yarasp.model.stations

data class Station(
    var direction: String = "",
    var codes: StationCodes? = StationCodes(),
    var stationType: String = "",
    var title: String = "",
    var longitude: Double = .0,
    var transportType: String = "",
    var latitude: Double = .0
)
