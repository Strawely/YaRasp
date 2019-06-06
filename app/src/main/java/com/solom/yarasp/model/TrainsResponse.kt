package com.solom.yarasp.model

data class TrainsResponse (
    var pagination: Pagination = Pagination(),
    var search: Search = Search(),
    var intervalSegments: MutableList<Segment> = arrayListOf(),
    var segments: MutableList<Segment> = arrayListOf()
)