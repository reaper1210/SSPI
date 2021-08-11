package com.petblowmachine.sspi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.petblowmachine.sspi.R

class MachineAdapter(private val itemList: ArrayList<String>, private val context: Context): RecyclerView.Adapter<MachineAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val machineName = view.findViewById<TextView>(R.id.txtSingleRowMachineName)
        val machineImg = view.findViewById<ImageView>(R.id.imgSingleRowMachineImage)
        val detailOne = view.findViewById<TextView>(R.id.txtSingleRowMachineDetailOne)
        val detailTwo = view.findViewById<TextView>(R.id.txtSingleRowMachineDetailTwo)
        val detailThree = view.findViewById<TextView>(R.id.txtSingleRowMachineDetailThree)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.machines_single_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.machineName.text = itemList[position]
        holder.machineImg.setImageResource(R.drawable.img1)
        holder.detailOne.text = "Price: Rs.150"
        holder.detailTwo.text = "Capacity: 5400BPH"
        holder.detailThree.text = "Usage: Industrial"
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}