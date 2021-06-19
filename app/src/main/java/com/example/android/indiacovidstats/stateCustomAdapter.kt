package com.example.android.indiacovidstats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class stateCustomAdapter() :RecyclerView.Adapter<ViewHolder>() {

    private var dataset = ArrayList<dataState>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.specific_state_wise,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.provinceState.text = dataset[position].provinceState
        holder.active.text = dataset[position].active
        holder.confirmed.text = dataset[position].confirmed
        holder.recovered.text = dataset[position].recovered
        holder.deaths.text = dataset[position].deaths
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun updateData(data: ArrayList<dataState>) {
        dataset.clear()
        dataset.addAll(data)

        notifyDataSetChanged()

    }
}

class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    val provinceState:TextView = itemView.findViewById(R.id.provinceState)
    val active:TextView = itemView.findViewById(R.id.activeNo)
    val confirmed:TextView = itemView.findViewById(R.id.confirmedNo)
    val recovered:TextView = itemView.findViewById(R.id.recoveredNo)
    val deaths:TextView = itemView.findViewById(R.id.deathsNo)
}