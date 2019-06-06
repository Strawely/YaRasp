package com.solom.yarasp.model.stations

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Country(
    var regions: MutableList<Region> = arrayListOf(),
    var codes: CountryCodes = CountryCodes(),
    var title: String = ""
)

