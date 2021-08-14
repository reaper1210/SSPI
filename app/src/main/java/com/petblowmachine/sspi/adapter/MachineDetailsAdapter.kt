package com.petblowmachine.sspi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.petblowmachine.sspi.R

class MachineDetailsAdapter(private val context: Context, private val itemList: ArrayList<String>): RecyclerView.Adapter<MachineDetailsAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val txtType: TextView = view.findViewById(R.id.machineDetailsSingleRowType)
        val txtInfo: TextView = view.findViewById(R.id.machineDetailsSingleRowInfo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.machine_details_single_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtType.text = itemList[position]
        holder.txtInfo.text = itemList[position]
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}