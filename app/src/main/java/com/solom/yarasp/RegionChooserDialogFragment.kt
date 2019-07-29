package com.solom.yarasp


import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class RegionChooserDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val items = arrayListOf(Repository.currentRegion.title)
        items.addAll(Repository.regions.filter { it != Repository.currentRegion }.map { it.title }.toList())
        builder.setTitle("Выберите регион")
            .setItems(items.toTypedArray()) { _, which ->
                Log.i(this.javaClass.simpleName, which.toString())
                if (which > 0){
                    Repository.currentRegion = Repository.regions.filter { it != Repository.currentRegion }[which - 1]
                    Repository.stationsList.clear()
                    Repository.currentRegion.settlements.forEach {
                        Repository.stationsList.addAll(it.stations.filter { station ->  station.transportType == "train" }.toList())
                    }
                }
            }
        return builder.create()
    }
}
