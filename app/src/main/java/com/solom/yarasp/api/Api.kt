package com.solom.yarasp.api

import com.solom.yarasp.model.TrainsResponse
import com.solom.yarasp.model.stations.StationsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("search")
    fun getTrains(
        @Query("apikey") apikey: String = com.solom.yarasp.apikey,
        @Query("format") format: String = "json",
        @Query("from") from: String = "s9606377",
        @Query("to") to: String = "s9606096",
        @Query("date") date: String = "2019-06-06",
        @Query("transport_types") transportTypes: String = "suburban"
    ): Call<TrainsResponse>

    @GET("stations_list")
    fun getStations(
        @Query("apikey") apikey: String = com.solom.yarasp.apikey,
        @Query("lang") lang: String = "ru_RU",
        @Query("format") format: String = "json"
    ): Call<StationsResponse>
}