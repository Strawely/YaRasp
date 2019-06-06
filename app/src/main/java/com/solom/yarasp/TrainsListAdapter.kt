package com.solom.yarasp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solom.yarasp.model.Segment
import org.jetbrains.anko.find

class TrainsListAdapter(
    val trainsList: MutableList<Segment> = arrayListOf()
) : RecyclerView.Adapter<TrainsListAdapter.TrainsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainsViewHolder {
        return TrainsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_train, parent, false))
    }

    override fun getItemCount() = trainsList.size

    override fun onBindViewHolder(holder: TrainsViewHolder, position: Int) {
        val train = trainsList[position]
        holder.textTrainName.text = train.thread.title
        holder.textDepartureTime.text = train.departure.substring(11, 16)
        holder.textArrivalTime.text = train.arrival.substring(11, 16)
    }

    class TrainsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textTrainName = view.find<TextView>(R.id.text_train_name)
        val textDepartureTime = view.find<TextView>(R.id.text_train_departure_time)
        val textArrivalTime = view.find<TextView>(R.id.text_train_arrival_time)
    }
}