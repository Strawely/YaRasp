package com.solom.yarasp.model

data class Thread (
    var interval: Interval = Interval(),
    var carrier: Carrier = Carrier(),
    var transportSubtype: TransportSubtype = TransportSubtype(),
    var uid: String = "",
    var title: String = "",
    var number: String = "",
    var shortTitle: String = "",
    var threadMethodLink: String = "",
    var transportType: String = "suburban",
    var vehicle: String = "",
    var expressType: String? = null
)