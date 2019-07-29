package com.solom.yarasp

import com.solom.yarasp.model.Segment
import com.solom.yarasp.model.stations.Country
import com.solom.yarasp.model.stations.Region
import com.solom.yarasp.model.stations.Station

object Repository{
    val trainsList: MutableList<Segment> = arrayListOf()
    val countries: MutableList<Country> = arrayListOf()
    var currentRegion: Region = Region()
    val regions: MutableList<Region> = arrayListOf()
    val stationsList: MutableList<Station> = arrayListOf()
}