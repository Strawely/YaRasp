package com.solom.yarasp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.solom.yarasp.model.stations.Station
import org.jetbrains.anko.find

class StationsAdapter(val stationsList: MutableList<Station> = arrayListOf(), private val context: Context?) :
    BaseAdapter(),
    Filterable {
    override fun getFilter() = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResults = FilterResults()
            if (constraint == null) return filterResults
            val filteredStations = mutableListOf<Station>()
            for (station in Repository.stationsList)
                if(station.title.contains(constraint))
                    filteredStations.add(station)
            filterResults.values = filteredStations
            filterResults.count = filteredStations.size
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results != null && results.count > 0) {
                stationsList.clear()
                stationsList.addAll(results.values as List<Station>)
                notifyDataSetChanged()
            } else {
                notifyDataSetInvalidated()
            }
        }

        override fun convertResultToString(resultValue: Any?): CharSequence {
            if (resultValue is Station) {
                return resultValue.title
            }
            return resultValue.toString()
        }
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_station, parent, false)
        val textTitle = view.find<TextView>(R.id.text_station_title)
        textTitle.text = getItem(position).title
        return view
    }

    override fun getItem(position: Int) = stationsList[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = stationsList.size

}