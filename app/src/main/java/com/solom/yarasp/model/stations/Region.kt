package com.solom.yarasp.model.stations

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Region(
    var settlements: MutableList<Settlement> = arrayListOf(),
    var codes: RegionCodes = RegionCodes(),
    var title: String = ""
)
