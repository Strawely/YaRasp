package com.solom.yarasp.model.stations

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StationCodes(
    var yandexCode: String = "",
    var esrCode: String = ""
)
