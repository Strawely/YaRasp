package com.solom.yarasp.model

data class Segment (
    var arrival: String = "",
    var departure: String = "",
    var departurePlatform: String? = null,
    var stops: String = "",
    var departureTerminal: String? = null,
    var hasTransfers: Boolean = false,
    var arrivalTerminal: String? = null,
    var from: SegmentStation = SegmentStation(),
    var to: SegmentStation = SegmentStation(),
    var thread: Thread = Thread(),
    var ticketsInfo: TicketsInfo = TicketsInfo(),
    var duration: Int = 0,
    var startDate: String = "",
    var arrivalPlatform: String = ""
)