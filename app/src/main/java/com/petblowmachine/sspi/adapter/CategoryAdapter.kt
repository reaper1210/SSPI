package com.petblowmachine.sspi.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.petblowmachine.sspi.MachinesActivity
import com.petblowmachine.sspi.R

class CategoryAdapter(val arrayList:ArrayList<String>, val context: Context):
    RecyclerView.Adapter<CategoryAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.category_single_row,parent,false)
        return ItemHolder(itemHolder)
    }

    override fun onBindViewHolder(holder:ItemHolder, position: Int) {
        val categoryName = arrayList[position]
        holder.button.setOnClickListener {
            val intent = Intent(context,MachinesActivity::class.java)
            context.startActivity(intent)
        }
        holder.categoryName.text = categoryName
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ItemHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var categoryImg = itemView.findViewById<ImageView>(R.id.imgCategory)
        var categoryName = itemView.findViewById<TextView>(R.id.txtCategoryName)
        var button = itemView.findViewById<ImageView>(R.id.btnViewAll)
    }
}