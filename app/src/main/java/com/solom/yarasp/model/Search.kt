package com.solom.yarasp.model

data class Search (
    var date: String = "",
    var to: SearchStation = SearchStation(),
    var from: SearchStation = SearchStation()
)