package com.example.android.indiacovidstats

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_news.*
import org.json.JSONObject


class News : AppCompatActivity(), onItemClickListener {

    lateinit var mAdapter: NewsCustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        fetchdata()

        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = NewsCustomAdapter(this)
        newsRecyclerView.adapter = mAdapter
    }

    fun fetchdata(){

        val url = "https://covid-19-news.p.rapidapi.com/v1/covid?q=covid&lang=en&country=IN&media=True"
        val jsonObject = object : JsonObjectRequest(Request.Method.GET, url,null,
            Response.Listener<JSONObject> {

                val dataset = ArrayList<dataNews>()
                val jsonArray = it.getJSONArray("articles")

                for(i in 0 until jsonArray.length()){

                    val json = jsonArray.getJSONObject(i)

                    val link = json.getString("link")
                    val media = json.getString("media")
                    val title =json.getString("title")
                    val clean_url = json.getString("clean_url")

                    val news = dataNews(
                        link,
                        media,
                        title,
                        clean_url
                    )

                    dataset.add(news)
                }
                mAdapter.update(dataset)
            },
            Response.ErrorListener {}
        )
        {
            override  fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["x-rapidapi-key"] = "Your API KEY, Check Readme"
                headers["x-rapidapi-host"] = "covid-19-news.p.rapidapi.com"
                return headers
            }
        }

        MySingleton.getInstance(this).addToRequestQueue(jsonObject)
    }

    override fun onItemClick(item: dataNews) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(
            this,
            Uri.parse(item.link)
        )
    }


}