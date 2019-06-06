package com.solom.yarasp.model.stations

data class Settlement(
    var title: String = "",
    var codes: SettlementCodes = SettlementCodes(),
    var stations: MutableList<Station> = arrayListOf()
)
