package com.example.android.indiacovidstats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import kotlinx.android.synthetic.main.activity_state_wise.*

class StateWise : AppCompatActivity() {

    private lateinit var mAdapter:stateCustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_wise)

        fetchData()

        stateRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = stateCustomAdapter()
        stateRecyclerView.adapter = mAdapter


    }

    private fun fetchData(){
        val url = "https://covid19.mathdro.id/api/countries/In/confirmed"

        val arrayReq = JsonArrayRequest(
            Request.Method.GET, url,null,
            {
                val dataset = ArrayList<dataState>()

                for(i in 0 until it.length()){

                    val json = it.getJSONObject(i)

                    val provinceState = json.getString("provinceState")
                    val active        = json.getString("active")
                    val confirmed     = json.getString("confirmed")
                    val recovered     = json.getString("recovered")
                    val deaths        = json.getString("deaths")

                    val data = dataState(
                        provinceState,
                        active,
                        confirmed,
                        recovered,
                        deaths)

                    dataset.add(data)

                }
                mAdapter.updateData(dataset)

            },

            {})
        MySingleton.getInstance(this).addToRequestQueue(arrayReq)
    }

}

