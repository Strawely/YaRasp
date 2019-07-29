package com.solom.yarasp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.solom.yarasp.api.ApiConnector
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)
        doAsync {
            ApiConnector.getStations()
            runOnUiThread {
                finish()
                startActivity<MainActivity>()
            }
        }
    }
}
