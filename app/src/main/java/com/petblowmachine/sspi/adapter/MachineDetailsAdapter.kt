package com.petblowmachine.sspi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.SnapshotMetadata
import com.petblowmachine.sspi.R

class MachineDetailsAdapter(private val context: Context, private val keyList: ArrayList<String>,private val valuesList:ArrayList<String>): RecyclerView.Adapter<MachineDetailsAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val txtType: TextView = view.findViewById(R.id.machineDetailsSingleRowType)
        val txtDesc: TextView = view.findViewById(R.id.machineDetailsSingleRowDesc)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.machine_details_single_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtType.text = keyList[position]
        holder.txtDesc.text = valuesList[position]
    }

    override fun getItemCount(): Int {
        return keyList.size
    }

}