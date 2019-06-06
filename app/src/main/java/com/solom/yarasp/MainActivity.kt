package com.solom.yarasp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.solom.yarasp.api.ApiConnector
import com.solom.yarasp.model.stations.Station
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MainActivity : FragmentActivity() {

    val trainsListAdapter = TrainsListAdapter()
    var departureStation = Station()
    var arrivalStation = Station()
    var date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doAsync {
            ApiConnector.getStations()
            Log.i("Stations loaded", "Stations loaded")
            runOnUiThread {
                toast("Stations loaded")
            }
        }

    }

    fun onDateClick(view: View){

    }

    fun onReverseClick(view: View) {
        val tmp = departureStation
        departureStation = arrivalStation
        arrivalStation = tmp
        val tmpTxt = edit_rasp_departure.text
        edit_rasp_departure.text = edit_rasp_arrival.text
        edit_rasp_arrival.text = tmpTxt
    }

    fun onSearchClick(view: View) {
        Log.d(this.javaClass.simpleName, "onSearchClick")
        date = SimpleDateFormat("dd.MM.yyyy").parse(edit_rasp_date.text.toString())
        doAsync {
            ApiConnector.getSchedule(departureStation, arrivalStation, date)
            runOnUiThread {
                trainsListAdapter.trainsList.clear()
                trainsListAdapter.notifyDataSetChanged()
                trainsListAdapter.trainsList.addAll(Repository.trainsList)
                trainsListAdapter.notifyDataSetChanged()
            }
        }
    }

}
