package com.solom.yarasp

import com.solom.yarasp.model.Segment
import com.solom.yarasp.model.stations.Station

object Repository{
    val trainsList: MutableList<Segment> = arrayListOf()
    val stationsList: MutableList<Station> = arrayListOf()
}