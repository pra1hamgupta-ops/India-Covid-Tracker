package com.example.android.indiacovidstats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val url = "https://covid19.mathdro.id/api/countries/In"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            {
                confirmedNo.text = it.getJSONObject("confirmed").getString("value").toString()
                recoveredNo.text = it.getJSONObject("recovered").getString("value").toString()
                deathsNo.text = it.getJSONObject("deaths").getString("value").toString()
                activeNo.text = ((confirmedNo.text as String).toInt() - (recoveredNo.text as String).toInt() - (deathsNo.text as String).toInt()).toString()
                updateTime.text = it.getString("lastUpdate").toString().substring(0,10)

            },
            {}
        )

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    fun stateWise(view: View) {
        val intent = Intent(this, StateWise::class.java)
        startActivity(intent)
    }
    fun news(view: View) {
        val intent = Intent(this, News::class.java)
        startActivity(intent)
    }
}
