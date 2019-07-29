package com.solom.yarasp


import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.solom.yarasp.model.stations.Station
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is MainActivity) {
            recycler_rasp_trains.adapter = (activity as MainActivity).trainsListAdapter
        }
        recycler_rasp_trains.layoutManager = LinearLayoutManager(context)

        edit_rasp_departure.threshold = 1
        edit_rasp_arrival.threshold = 1

        edit_rasp_departure.setAdapter(StationsAdapter(context = context))
        edit_rasp_departure.setOnItemClickListener { parent, view, position, id ->
            val station = parent.getItemAtPosition(position) as Station
            edit_rasp_departure.setText(station.title)
            if (activity is MainActivity) {
                (activity as MainActivity).departureStation = station
            }
        }

        edit_rasp_arrival.setAdapter(StationsAdapter(context = context))
        edit_rasp_arrival.setOnItemClickListener { parent, view, position, id ->
            val station = parent.getItemAtPosition(position) as Station
            edit_rasp_arrival.setText(station.title)
            if (activity is MainActivity) {
                (activity as MainActivity).arrivalStation = station
            }
        }

    }
}
