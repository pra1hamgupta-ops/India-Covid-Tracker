package com.example.android.indiacovidstats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsCustomAdapter(val listener: onItemClickListener): RecyclerView.Adapter<NewsViewHolder>() {

    val dataset = ArrayList<dataNews>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.specific_news,parent,false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(dataset[position].media).into(holder.newsImage)
        holder.newsTitle.text = dataset[position].title
        holder.newsWeb.text = dataset[position].clean_url

        holder.getView().setOnClickListener {
            listener.onItemClick(dataset[position])
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun update(data:ArrayList<dataNews>){
        dataset.clear()
        dataset.addAll(data)
        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val newsImage:ImageView = itemView.findViewById(R.id.newsImage)
    val newsTitle:TextView = itemView.findViewById(R.id.newsTitle)
    val newsWeb:TextView = itemView.findViewById(R.id.newsWeb)

    fun getView():View{
        return itemView
    }
}

interface onItemClickListener{
    fun onItemClick(item:dataNews)
}