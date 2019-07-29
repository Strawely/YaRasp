package com.solom.yarasp

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.material.navigation.NavigationView
import com.solom.yarasp.api.ApiConnector
import com.solom.yarasp.model.stations.Station
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : FragmentActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var actionbarDrawerToggleButton: ActionBarDrawerToggle

    val trainsListAdapter = TrainsListAdapter()
    var departureStation = Station()
    var arrivalStation = Station()
    private var date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation_rasp.setNavigationItemSelectedListener(this)
        navigation_rasp.bringToFront()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val toolbar = find<Toolbar>(R.id.toolbar_rasp)
        actionbarDrawerToggleButton =
            ActionBarDrawerToggle(this, layout_rasp_drawer, toolbar, R.string.open_drawer, R.string.close_drawer)
        layout_rasp_drawer.addDrawerListener(actionbarDrawerToggleButton)
        actionbarDrawerToggleButton.syncState()
    }

    fun onDateClick(view: View) {
        DatePickerFragment().show(supportFragmentManager, "datePicker")
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
        if(edit_rasp_departure.text.isNullOrBlank()) toast("Заполните поле станции отправления")
        if(edit_rasp_arrival.text.isNullOrBlank()) toast("Заполните поле станции прибытия")
        if(edit_rasp_date.text.isNullOrBlank()) toast("Заполните поле даты поездки")
        doAsync {
            ApiConnector.getSchedule(departureStation, arrivalStation, date)
            runOnUiThread {
                trainsListAdapter.trainsList.clear()
                trainsListAdapter.notifyDataSetChanged()
                trainsListAdapter.trainsList.addAll(Repository.trainsList)
                trainsListAdapter.notifyDataSetChanged()
            }
        }
        edit_rasp_arrival.clearFocus()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        return when (p0.itemId) {
            R.id.menu_main_region -> {
                RegionChooserDialogFragment().show(supportFragmentManager, "regionPicker")
                layout_rasp_drawer.closeDrawer(GravityCompat.START)
                true
            }
            else -> false
        }
    }

    fun onFavouriteClick(view: View){
         /*
         * По нажатию сохранить список поездов в БД
         * При включении оффлайн режима:
         *  в подсказках только станции из оффлайна*/
    }
}
