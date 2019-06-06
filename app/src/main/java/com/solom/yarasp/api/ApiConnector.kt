package com.solom.yarasp.api

import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.solom.yarasp.DoubleTypeAdapter
import com.solom.yarasp.Repository
import com.solom.yarasp.model.TrainsResponse
import com.solom.yarasp.model.stations.Country
import com.solom.yarasp.model.stations.Station
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.log

object ApiConnector {
    private val logTag = "ApiConnector"

    private val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .registerTypeAdapter(Double::class.java, DoubleTypeAdapter())
        .create()

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl("https://api.rasp.yandex.net/v3.0/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val yaApi = retrofit.create(Api::class.java)

    fun getSchedule(start: Station, end: Station, date: Date) {
//        println("$logTag : getting schedule")
        Log.i(logTag, "getting schedule")
        val response = yaApi.getTrains(from = start.codes?.yandexCode!!, to = end.codes?.yandexCode!!, date = SimpleDateFormat("yyyy-MM-dd").format(date)).execute()
        if (response.isSuccessful) {
            println(response.body())
            Repository.trainsList.clear()
            Repository.trainsList.addAll(response.body()?.segments ?: arrayListOf())
        } else {
            print("$logTag : ${response.errorBody()}")
        }
    }

    fun getStations() {
        Log.i(logTag, "getting stations...")
        val response = yaApi.getStations().execute()
        if (response.isSuccessful) {
            Repository.stationsList.clear()
            val countries = response.body()?.countries ?: arrayListOf()
            val settlements = countries.find { it.codes.yandexCode == "l225" }!!
                .regions
                .find { it.codes.yandexCode == "r11131" }!!
                .settlements
                .forEach {
                    it.stations.filter { station -> station.transportType == "train" }
                        .forEach{ station ->
                            Repository.stationsList.add(station)
                        }
                }

//                for (country in countries)
//                    if (country.codes.yandexCode == "l225")
//                        for (region in country.regions)
//                            if (region.codes.yandexCode ==)
//                                for (settlement in region.settlements)
//                                    for (station in settlement.stations)
//                                        if (station.transportType == "train")
//                                            Repository.stationsList.add(station)


        } else {
            print("$logTag : ${response.errorBody()}")
        }
    }
}
