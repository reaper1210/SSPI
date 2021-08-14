package com.petblowmachine.sspi.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.petblowmachine.sspi.R
import com.petblowmachine.sspi.activities.MachineDetailsActivity

class MachineAdapter(private val context: Context, private val itemList: ArrayList<String>): RecyclerView.Adapter<MachineAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val machineName: TextView = view.findViewById(R.id.txtSingleRowMachineName)
        val machineImg: ImageView = view.findViewById(R.id.imgSingleRowMachineImage)
        val detailOne: TextView = view.findViewById(R.id.txtSingleRowMachineDetailOne)
        val detailTwo: TextView = view.findViewById(R.id.txtSingleRowMachineDetailTwo)
        val detailThree: TextView = view.findViewById(R.id.txtSingleRowMachineDetailThree)
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
        holder.itemView.setOnClickListener {
            val intent = Intent(context,MachineDetailsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}