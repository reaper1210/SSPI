package com.petblowmachine.sspi.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.petblowmachine.sspi.R
import com.petblowmachine.sspi.activities.MachineDetailsActivity
import com.petblowmachine.sspi.modal.Applic
import com.petblowmachine.sspi.modal.MachineInfo
import com.google.android.gms.common.data.DataHolder




class MachineAdapter(private val context: Context, private var itemList: ArrayList<MachineInfo>):
    RecyclerView.Adapter<MachineAdapter.ViewHolder>() {

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
        val currentMachine = itemList[position]
        Glide.with(context).load(currentMachine.machineImg).centerCrop().error(R.drawable.no_img).into(holder.machineImg)
        holder.machineName.text = currentMachine.machineName
        holder.detailOne.text = currentMachine.detail1
        holder.detailTwo.text = currentMachine.detail2
        holder.detailThree.text = currentMachine.detail3
        holder.itemView.setOnClickListener {
            val intent = Intent(context,MachineDetailsActivity::class.java)
            Applic.machineName = currentMachine.machineName
            Applic.machineImg = currentMachine.machineImg
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateList(list: ArrayList<MachineInfo>) {
        itemList = list
        notifyDataSetChanged()
    }

}